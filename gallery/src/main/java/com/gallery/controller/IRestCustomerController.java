package com.gallery.controller;

import com.gallery.dto.DtoCustomer;
import com.gallery.dto.DtoCustomerIU;

public interface IRestCustomerController {

	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
