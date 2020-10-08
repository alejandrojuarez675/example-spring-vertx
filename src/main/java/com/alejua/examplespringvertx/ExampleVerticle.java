package com.alejua.examplespringvertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

@Component
public class ExampleVerticle extends AbstractVerticle {

	private static final Logger logger = LoggerFactory.getLogger(ExampleVerticle.class);

	public static final String ADD_SALUDO = "example.saludo.address";
	public static final String ADD_CUSTOM_SALUDO = "example.saludo.custom.address";
	
	@Autowired
	ExampleService exampleService;
	
	@Override
	public void start() throws Exception {
		logger.info("Init ExampleVerticle");
		super.start();
		
		vertx.eventBus().consumer(ADD_SALUDO, this::eventTargetSaludo);
		vertx.eventBus().consumer(ADD_CUSTOM_SALUDO, this::eventTargetCustomSaludo);
	}

	private void eventTargetSaludo(Message<String> msg) {
		logger.info("Event " + ADD_SALUDO);
		
		vertx.<String> executeBlocking(future -> {
            future.complete(exampleService.getSaludo());
	    }, result -> {
	        if (result.succeeded()) {
	            msg.reply(result.result());
	        } else {
	            msg.reply(result.cause().toString());
	        }
	    });
	}
	
	private void eventTargetCustomSaludo(Message<String> msg) {
		logger.info("Event " + ADD_CUSTOM_SALUDO);
		
		vertx.<String> executeBlocking(future -> {
            future.complete(exampleService.getCustomSaludo(msg.body()));
	    }, result -> {
	        if (result.succeeded()) {
	            msg.reply(result.result());
	        } else {
	            msg.reply(result.cause().toString());
	        }
	    });
	}

}
