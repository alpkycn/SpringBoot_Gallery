package com.gallery.controller;

import com.gallery.dto.AuthRequest;
import com.gallery.dto.AuthResponse;
import com.gallery.dto.DtoUser;
import com.gallery.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
