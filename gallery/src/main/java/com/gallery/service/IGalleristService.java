package com.gallery.service;

import com.gallery.dto.DtoGallerist;
import com.gallery.dto.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
