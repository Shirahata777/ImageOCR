package com.github.shirahata777;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.shirahata777.controller.MainController;

@SpringBootApplication
public class ImageOcrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainController.class, args);
	}

}
