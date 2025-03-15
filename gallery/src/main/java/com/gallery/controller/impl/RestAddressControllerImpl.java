package com.gallery.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gallery.controller.IRestAddressController;
import com.gallery.controller.RestBaseController;
import com.gallery.controller.RootEntity;
import com.gallery.dto.DtoAddress;
import com.gallery.dto.DtoAddressIU;
import com.gallery.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{

	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}

}
