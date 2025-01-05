package com.lr0cha.park_api.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class SpringTimezoneConfig {

	@PostConstruct
	private void timezoneConfig() {
		TimeZone.setDefault(TimeZone.getTimeZone("América/São_Paulo"));
	}
}
