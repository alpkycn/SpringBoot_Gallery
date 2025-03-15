package com.gallery.service;

import com.gallery.dto.DtoGalleristCar;
import com.gallery.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
