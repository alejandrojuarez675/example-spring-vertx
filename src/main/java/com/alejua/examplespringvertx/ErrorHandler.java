package com.alejua.examplespringvertx;

import io.vertx.ext.web.RoutingContext;

public class ErrorHandler {

	public static void notFountError(RoutingContext ctx) {
		System.out.println("La ruta no existe");
		ctx.response().setStatusCode(404).end("La pag no existe");
	}
	
}
