package com.alejua.examplespringvertx;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

	public String getSaludo() {
		System.out.println("ExampleService::getSaludo");
		return "Hola desde Service";
	}
	
}
