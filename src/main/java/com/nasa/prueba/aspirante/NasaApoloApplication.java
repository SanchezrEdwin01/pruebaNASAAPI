package com.nasa.prueba.aspirante;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class NasaApoloApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaApoloApplication.class, args);
	}

}
