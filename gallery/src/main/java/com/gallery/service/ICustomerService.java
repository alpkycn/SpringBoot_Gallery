package com.gallery.service;

import com.gallery.dto.DtoCustomer;
import com.gallery.dto.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
