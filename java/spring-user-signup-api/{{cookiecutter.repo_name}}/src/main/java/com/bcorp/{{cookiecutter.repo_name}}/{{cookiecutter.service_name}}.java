package com.bcorp.signup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class {{ cookiecutter.service_name }} {

	public static void main(String[] args) {
		SpringApplication.run({{ cookiecutter.service_name }}.class, args);
	}

}
