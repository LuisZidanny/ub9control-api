package br.com.applicationfinancialmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import br.com.applicationfinancialmanagement.config.property.ApplicationfinancialmanagementApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationfinancialmanagementApiProperty.class)
public class ApplicationfinancialmanagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationfinancialmanagementApiApplication.class, args);
	}

}
