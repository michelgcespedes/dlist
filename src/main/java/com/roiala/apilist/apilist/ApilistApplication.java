package com.roiala.apilist.apilist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@ComponentScan(basePackages = "com.roiala.apilist.apilist")
@SpringBootApplication
@EntityScan("com.roiala.apilist.apilist.*")
public class ApilistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApilistApplication.class, args);
	}

}
