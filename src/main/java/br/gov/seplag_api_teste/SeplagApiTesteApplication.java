package br.gov.seplag_api_teste;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SeplagApiTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeplagApiTesteApplication.class, args);
	}

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Cuiaba"));
	}
}
