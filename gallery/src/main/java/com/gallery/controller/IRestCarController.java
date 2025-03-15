package com.gallery.controller;

import com.gallery.dto.DtoCar;
import com.gallery.dto.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
