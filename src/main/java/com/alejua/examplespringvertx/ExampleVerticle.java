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

	public static final String ADDR_SALUDO = "example.saludo.address";
	public static final String ADDR_CUSTOM_SALUDO = "example.saludo.custom.address";
	
	@Autowired
	ExampleService exampleService;
	
	@Override
	public void start() throws Exception {
		logger.info("Init ExampleVerticle");
		super.start();
		
		vertx.eventBus().consumer(ADDR_SALUDO, this::eventTargetSaludo);
		vertx.eventBus().consumer(ADDR_CUSTOM_SALUDO, this::eventTargetCustomSaludo);
	}

	private void eventTargetSaludo(Message<String> msg) {
		logger.info("Event " + ADDR_SALUDO);
		
		vertx.<String>executeBlocking(future -> {
			future.complete(exampleService.getSaludo());
		}, result -> {
			if (result.succeeded()) {
				msg.reply(result.result());
			} else {
				logger.error(result.cause().getMessage());
				msg.fail(0, result.cause().getMessage());
			}
		});
	}
	
	private void eventTargetCustomSaludo(Message<String> msg) {
		logger.info("Event " + ADDR_CUSTOM_SALUDO);
		
		vertx.<String>executeBlocking(future -> {
			future.complete(exampleService.getCustomSaludo(msg.body()));
		}, result -> {
			if (result.succeeded()) {
				msg.reply(result.result());
			} else {
				logger.error(result.cause().getMessage());
				msg.fail(0, result.cause().getMessage());
			}
		});
	}

}
