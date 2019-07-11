package com.ibm.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ibm.rest.property.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class IBMBusinessValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IBMBusinessValidatorApplication.class, args);
	}
}
