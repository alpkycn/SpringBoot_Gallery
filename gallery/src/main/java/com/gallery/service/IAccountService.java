package com.gallery.service;

import com.gallery.dto.DtoAccount;
import com.gallery.dto.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
