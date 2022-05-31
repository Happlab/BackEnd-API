package co.edu.unicauca.APIHappLab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApiHappLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHappLabApplication.class, args);
	}

}
