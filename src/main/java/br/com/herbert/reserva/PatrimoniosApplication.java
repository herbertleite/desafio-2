package br.com.herbert.reserva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.herbert.reserva.repository")
public class PatrimoniosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrimoniosApplication.class, args);
	}

}
