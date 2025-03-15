package com.gallery.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gallery.controller.IRestAccountController;
import com.gallery.controller.RestBaseController;
import com.gallery.controller.RootEntity;
import com.gallery.dto.DtoAccount;
import com.gallery.dto.DtoAccountIU;
import com.gallery.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountContollerImpl extends RestBaseController implements IRestAccountController {

	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
		return ok(accountService.saveAccount(dtoAccountIU));
	}

}
