package com.gallery.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase{

	private String firstName;
	
	private String lastName;
		
	private Date birthOfDate;
	
	private DtoAddress address;
	
	private DtoAccount  account;
}
