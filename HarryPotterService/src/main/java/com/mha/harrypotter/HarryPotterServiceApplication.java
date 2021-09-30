package com.mha.harrypotter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "com.mha.harrypotter" })
public class HarryPotterServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HarryPotterServiceApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HarryPotterServiceApplication.class, args);
	}

}
