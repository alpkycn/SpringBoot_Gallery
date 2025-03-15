package com.gallery.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gallery.dto.CurrencyRatesItems;
import com.gallery.dto.CurrencyRatesResponse;
import com.gallery.exception.BaseException;
import com.gallery.exception.ErrorMessage;
import com.gallery.exception.MessageType;
import com.gallery.service.ICurrencyRatesService;




@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {


	
	 @Override
	    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
	        String today = LocalDate.now().toString();
	        String endpoint = "https://api.frankfurter.app/" + today + "?from=USD&to=EUR";

	        try {
	            RestTemplate restTemplate = new RestTemplate();
	            ResponseEntity<Map> response = restTemplate.getForEntity(endpoint, Map.class);


	            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	                Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");

	                if (rates == null || !rates.containsKey("EUR") || rates.get("EUR") == null) {
	                    throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, "EURO exchange rate not found in response!"));
	                }

	                BigDecimal usdToEur = new BigDecimal(rates.get("EUR").toString());

	                CurrencyRatesResponse currencyRatesResponse = new CurrencyRatesResponse();
	                currencyRatesResponse.setUsdToEurRate(usdToEur);

	                CurrencyRatesItems currencyRatesItem = new CurrencyRatesItems();
	                currencyRatesItem.setDate(response.getBody().get("date").toString());
	                currencyRatesItem.setUsd(usdToEur.toString());

	                List<CurrencyRatesItems> items = new ArrayList<>();
	                items.add(currencyRatesItem);
	                currencyRatesResponse.setItems(items);

	                return currencyRatesResponse;
	            }
	        } catch (Exception e) {
	            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, "Could not get exchange rate: " + e.getMessage()));
	        }
	        return null;
	    }
	
	
/*   @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String endpoint = "https://api.frankfurter.app/latest?from=USD&to=EUR"; 

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.getForEntity(endpoint, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> rates = (Map<String, Object>) response.getBody().get("rates");
                BigDecimal usdToEur = new BigDecimal(rates.get("EURO").toString());

                CurrencyRatesResponse currencyRatesResponse = new CurrencyRatesResponse();
                currencyRatesResponse.setUsdToEurRate(usdToEur);

                CurrencyRatesItems currencyRatesItem = new CurrencyRatesItems();
                currencyRatesItem.setDate(response.getBody().get("date").toString()); 
                currencyRatesItem.setUsd(rates.get("EURO").toString());

                List<CurrencyRatesItems> items = new ArrayList<>();
                items.add(currencyRatesItem);
                currencyRatesResponse.setItems(items);

                return currencyRatesResponse;
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
        return null;
    }
*/    
}