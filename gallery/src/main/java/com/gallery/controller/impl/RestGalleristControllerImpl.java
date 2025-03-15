package com.gallery.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gallery.controller.IRestGalleristController;
import com.gallery.controller.RestBaseController;
import com.gallery.controller.RootEntity;
import com.gallery.dto.DtoGallerist;
import com.gallery.dto.DtoGalleristIU;
import com.gallery.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristControllerImpl extends RestBaseController implements IRestGalleristController{

	@Autowired
	private IGalleristService galleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

}
