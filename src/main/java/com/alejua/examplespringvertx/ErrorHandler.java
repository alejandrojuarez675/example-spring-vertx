package com.alejua.examplespringvertx;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;

public class ErrorHandler {

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

	public static void notFountError(RoutingContext ctx) {
		logger.error("La ruta no existe");
		ctx.response().setStatusCode(404).end("La pag no existe");
	}
	
}
