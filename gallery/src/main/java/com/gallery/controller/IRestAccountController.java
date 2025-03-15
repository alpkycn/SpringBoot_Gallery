package com.gallery.controller;

import com.gallery.dto.DtoAccount;
import com.gallery.dto.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
