package com.gallery.controller;

import com.gallery.dto.DtoAddress;
import com.gallery.dto.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
