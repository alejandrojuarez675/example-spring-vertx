package com.alejua.examplespringvertx;

import org.springframework.stereotype.Service;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

@Service
public class ExampleService {

	private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

	public String getSaludo() {
		logger.info("ExampleService::getSaludo");
		return "Hola desde Service";
	}

	public String getCustomSaludo(String name) {
		logger.info("ExampleService::getCustomSaludo");
		return String.format("Hola %s desde Service", name);	
	}
	
}
