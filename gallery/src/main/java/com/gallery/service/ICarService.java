package com.gallery.service;

import com.gallery.dto.DtoCar;
import com.gallery.dto.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
}
