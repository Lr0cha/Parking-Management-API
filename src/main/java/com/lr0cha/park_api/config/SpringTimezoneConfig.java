package com.lr0cha.park_api.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration //identificar que é uma configuração 
public class SpringTimezoneConfig {

	@PostConstruct //executado logo após a injeção de dependências do Spring
	private void timezoneConfig() {
		TimeZone.setDefault(TimeZone.getTimeZone("América/São_Paulo"));
	}
}
