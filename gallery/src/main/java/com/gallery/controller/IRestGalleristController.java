package com.gallery.controller;

import com.gallery.dto.DtoGallerist;
import com.gallery.dto.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
