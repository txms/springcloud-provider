package com.bitsun.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ProviderApplication {

	public static void main(String[] args) {

		//解决es与netty冲突
		System.setProperty("es.set.netty.runtime.available.processors", "false");

		SpringApplication.run(ProviderApplication.class, args);
	}

}
