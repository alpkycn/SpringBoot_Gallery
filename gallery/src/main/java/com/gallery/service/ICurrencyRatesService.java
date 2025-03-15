package com.gallery.service;

import com.gallery.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate , String endDate);
}
