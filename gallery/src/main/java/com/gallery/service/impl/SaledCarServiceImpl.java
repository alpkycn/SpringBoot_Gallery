package com.gallery.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gallery.dto.CurrencyRatesResponse;
import com.gallery.dto.DtoCar;
import com.gallery.dto.DtoCustomer;
import com.gallery.dto.DtoGallerist;
import com.gallery.dto.DtoSaledCar;
import com.gallery.dto.DtoSaledCarIU;
import com.gallery.enums.CarStatusType;
import com.gallery.exception.BaseException;
import com.gallery.exception.ErrorMessage;
import com.gallery.exception.MessageType;
import com.gallery.model.Account;
import com.gallery.model.Car;
import com.gallery.model.Customer;
import com.gallery.model.SaledCar;
import com.gallery.repository.CarRepository;
import com.gallery.repository.CustomerRepository;
import com.gallery.repository.GalleristRepository;
import com.gallery.repository.SaledCarRepository;
import com.gallery.service.ICurrencyRatesService;
import com.gallery.service.ISaledCarService;

import jakarta.transaction.Transactional;



@Service
public class SaledCarServiceImpl implements ISaledCarService {
	
	@Autowired
	private SaledCarRepository saledCarRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
	    String today = getCurrentDate(); 
	    
	    CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(today, today);
	    

	    BigDecimal usdToEurRate = currencyRatesResponse.getUsdToEurRate();
	    if (usdToEurRate == null) {
	        throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, "No exchange rate data found!"));
	    }
	    
	    BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
	    return customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
	}
	
	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}
	
	public BigDecimal remaningCustomerAmount(Customer customer, Car car) {
	    BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
	    BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

	    String today = getCurrentDate();

	    CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(today, today);
	    BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

	    return remaningCustomerUSDAmount.multiply(usd);
	}
	
	private String getCurrentDate() {
	    LocalDate today = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    return today.format(formatter);
	}

	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}

		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

		
		if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
				|| customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}
		return false;

	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTime(new Date());
		
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		
		return saledCar;
	}

	@Transactional
	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		
		if(!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		
		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		carRepository.save(car);
		
		Customer customer = savedSaledCar.getCustomer();
		Account account = customer.getAccount();
		account.setAmount(remaningCustomerAmount(customer, car));
		customer.setAccount(account); 
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}
	
	
	public DtoSaledCar toDTO(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);
		
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);
		return dtoSaledCar;
	}

}
