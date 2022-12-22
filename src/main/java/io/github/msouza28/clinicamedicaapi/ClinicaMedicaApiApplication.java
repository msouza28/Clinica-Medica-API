package io.github.msouza28.clinicamedicaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ClinicaMedicaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaMedicaApiApplication.class, args);
	}

}
