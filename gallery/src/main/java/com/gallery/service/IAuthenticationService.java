package com.gallery.service;

import com.gallery.dto.AuthRequest;
import com.gallery.dto.AuthResponse;
import com.gallery.dto.DtoUser;
import com.gallery.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
