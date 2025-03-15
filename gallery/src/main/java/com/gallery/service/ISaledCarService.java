package com.gallery.service;

import com.gallery.dto.DtoSaledCar;
import com.gallery.dto.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
