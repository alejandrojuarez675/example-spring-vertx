package com.alejua.examplespringvertx;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.vertx.core.Vertx;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ExampleSpringVertxApplication {

	@Autowired
	HttpServerVerticle httpServerVerticle;

	@Autowired
	ExampleVerticle exampleVerticle;

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringVertxApplication.class, args);
	}

	@PostConstruct
	public void deployVerticle() {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(httpServerVerticle);
		vertx.deployVerticle(exampleVerticle);
	}

}
