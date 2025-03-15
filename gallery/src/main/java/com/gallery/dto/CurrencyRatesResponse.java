package com.gallery.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesResponse {
	
	private Integer totalCount;
	
	private List<CurrencyRatesItems> items;
	
	private BigDecimal usdToEurRate;
	
}
