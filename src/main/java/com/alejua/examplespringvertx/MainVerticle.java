package com.alejua.examplespringvertx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

@Component
public class MainVerticle extends AbstractVerticle {

	private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);

	@Autowired
	ExampleVerticle exampleVerticle;
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		logger.info("deploy verticles generales");
		vertx.deployVerticle(new HttpServerVerticle());
		vertx.deployVerticle(new RedisVerticle());
		
		logger.info("deploy verticles con SpringFramework");
		vertx.deployVerticle(exampleVerticle);

	}
}
