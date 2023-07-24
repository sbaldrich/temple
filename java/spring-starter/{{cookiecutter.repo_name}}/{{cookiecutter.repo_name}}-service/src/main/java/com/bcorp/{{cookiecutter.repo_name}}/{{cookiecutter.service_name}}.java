package com.bcorp.{{ cookiecutter.repo_name }};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class {{ cookiecutter.service_name }} {

	public static void main(String[] args) {
		SpringApplication.run({{ cookiecutter.service_name }}.class, args);
	}

}
