package com.alejua.examplespringvertx;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class ExampleRouter implements Handler<RoutingContext> {
	
	private static final Logger logger = LoggerFactory.getLogger(ExampleRouter.class);
	
	private Router router;

	public ExampleRouter(Vertx vertx, Router router, String path) {
		logger.info("Init exampleRouter");
		this.router = router;

		router.get(path + "/saludo").handler(ctx -> getSaludo(ctx, vertx));
		router.get(path + "/saludo/:name").handler(ctx -> getCustomSaludo(ctx, vertx));

	}

	private void getSaludo(RoutingContext ctx, Vertx vertx) {
		logger.info("Llamo a /example/saludo");

		logger.info("Request Event " + ExampleVerticle.ADD_SALUDO);
		vertx.eventBus().request(ExampleVerticle.ADD_SALUDO, "", reply -> {
			if (reply.succeeded()) {
				
				SaludoDTO saludoDTO = new SaludoDTO(reply.result().body().toString());
				
				ctx.response()
					.putHeader("content-type", "application/json")
					.end(JsonObject.mapFrom(saludoDTO).encode());
				
			} else {
				ctx.response().setStatusCode(500).setStatusMessage("Internal error").end();
			}
		});
	}
	
	private void getCustomSaludo(RoutingContext ctx, Vertx vertx) {
		logger.info("Llamo a /example/saludo/:name");

		String name = ctx.pathParam("name");
		
		logger.info("Request Event " + ExampleVerticle.ADD_CUSTOM_SALUDO);
		vertx.eventBus().request(ExampleVerticle.ADD_CUSTOM_SALUDO, name, reply -> {
			if (reply.succeeded()) {
				SaludoDTO saludoDTO = new SaludoDTO(reply.result().body().toString());
				
				ctx.response()
					.putHeader("content-type", "application/json")
					.end(JsonObject.mapFrom(saludoDTO).encode());
				
			} else {
				logger.error("ERROR: " + reply.cause().getMessage());
				ctx.response().setStatusCode(500).setStatusMessage("Internal error").end();
			}
		});
	}

	@Override
	public void handle(RoutingContext ctx) {
		router.handleContext(ctx);
	}

}
