package com.gallery.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004" , "No record found"),
	TOKEN_IS_EXPIRED("1005" , "Token is expired"),
	USERNAME_NOT_FOUND("1006" , "Username not found"),
	USERNAME_OR_PASSWORD_INVALID("1007" , "Username or password is incorrect"),
	REFRESH_TOKEN_NOT_FOUND("1008" , "Refresh token not found"),
	REFRESH_TOKEN_IS_EXPIRED("1009" , "Refresh token is expired"),
	CURRENCY_RATES_IS_OCCURED("1010" , "Could not get exchange rate"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011" , "The customer's money is not enough"),
	CAR_STATUS_IS_ALREADY_SALED("1012" , "The car cannot be sold because it is sold."),
	GENERAL_EXCEPTION("9999" , "An error occured");
	
	private String code;
	private String message;
	
	 MessageType(String code , String message) {
		this.code =code;
		this.message = message;
	}
}
