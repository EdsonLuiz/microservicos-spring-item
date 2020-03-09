package com.edson.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ServicoItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoItemApplication.class, args);
	}

}
