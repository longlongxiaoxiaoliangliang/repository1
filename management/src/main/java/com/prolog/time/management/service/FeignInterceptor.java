package com.prolog.time.management.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prolog.framework.authority.core.service.TokenService;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignInterceptor implements RequestInterceptor {

	@Autowired
	private TokenService tokenService;
	
	@Override
	public void apply(RequestTemplate requestTemplate) {
		String token;
		try {
			token = tokenService.getToken();
			requestTemplate.header("Authorization", "Bearer "+token);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
