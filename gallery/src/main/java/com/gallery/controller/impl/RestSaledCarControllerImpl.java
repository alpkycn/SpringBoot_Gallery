package com.gallery.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gallery.controller.IRestSaledCarController;
import com.gallery.controller.RestBaseController;
import com.gallery.controller.RootEntity;
import com.gallery.dto.DtoSaledCar;
import com.gallery.dto.DtoSaledCarIU;
import com.gallery.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController{

	@Autowired
	private ISaledCarService saledCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}

}
