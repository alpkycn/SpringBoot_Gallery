package com.gallery.controller;

import com.gallery.dto.DtoSaledCar;
import com.gallery.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
