package com.gallery.service;

import com.gallery.dto.DtoAddress;
import com.gallery.dto.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
