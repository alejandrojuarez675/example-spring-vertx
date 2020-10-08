package com.alejua.examplespringvertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

@Component
public class ExampleVerticle extends AbstractVerticle {

	public static final String ADD_SALUDO = "example.saludo.address";
	
	@Autowired
	ExampleService exampleService;
	
	@Override
	public void start() throws Exception {
		System.out.println("Init ExampleVerticle");
		super.start();
		
		vertx.eventBus().consumer(ADD_SALUDO, this::eventTargetSaludo);
	}

	private void eventTargetSaludo(Message<String> msg) {
		System.out.println("Event " + ADD_SALUDO);
		
		vertx.<String> executeBlocking(future -> {
			System.out.println("Entro a future");
			
			if (exampleService == null) {
				System.out.println("service vacio");
			}
			
            future.complete(exampleService.getSaludo());
	    }, result -> {
			System.out.println("Result of service");
	        if (result.succeeded()) {
	            msg.reply(result.result());
	        } else {
	            msg.reply(result.cause().toString());
	        }
	    });
	}
}
