package com.alejua.examplespringvertx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

@Service
public class ExampleService {

	private static final Logger logger = LoggerFactory.getLogger(ExampleService.class);

	@Autowired
	UserRepository userRepository;
	
	public String getSaludo() {
		logger.info("ExampleService::getSaludo");
		return "Hola desde Service";
	}

	public String getCustomSaludo(String name) {
		logger.info("ExampleService::getCustomSaludo");
		return String.format("Hola %s desde Service", name);	
	}
	
	public List<Users> getUsers() {
		logger.info("ExampleService::getUsers");
		return userRepository.findAll();
	}
	
}
