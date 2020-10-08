package com.alejua.examplespringvertx;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

@Component
public class HttpServerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		System.out.println("Init HttpServerVerticle");
		super.start();

		System.out.println("creo httpServer");
		HttpServer server = vertx.createHttpServer();

		System.out.println("init rutas");
		Router router = Router.router(vertx);
		router.route("/example").handler(new ExampleRouter(vertx, router, "/example"));
		router.route().handler(ErrorHandler::notFountError);
		
		server.requestHandler(router).listen(
				config().getInteger("http.port", 8080), 
				http -> {
					if (http.succeeded()) {
						startPromise.complete();
						System.out.println(String.format(
								"HTTP server started on port %s", 
								config().getInteger("http.port", 8080)));
					} else {
						startPromise.fail(http.cause());
					}
				});

	}

}
