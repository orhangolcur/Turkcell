package com.turkcell.spring_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Annotation => Bulunduğu class, fonks., değişken'e özellik kazandırır. SpringBootApplication => SpringBoot'un temel özelliklerini kazandırır.
public class SpringStarterApplication {

	// Entrypoint => Uygulamanın çalışmaya başlayacağı nokta.
	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}

}
