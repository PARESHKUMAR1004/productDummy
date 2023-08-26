package com.wellsfargo.training.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProductResapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductResapiApplication.class, args);
	}

}
