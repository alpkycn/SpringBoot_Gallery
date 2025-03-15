package com.gallery.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRatesItems {

	@JsonProperty("Date")
	private String date;
	
//	@JsonProperty("EUR_DK_USD_A")
	private String usd;
}
