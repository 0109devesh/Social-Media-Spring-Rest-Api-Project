package com.smapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@EnableWebSecurity
@ServletComponentScan
public class SmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmApiApplication.class, args);
	}

}
