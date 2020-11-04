package br.com.agibank.analisadordedados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AnalisadorDeDadosApplication {
	public static void main(String[] args) {
		SpringApplication.run(AnalisadorDeDadosApplication.class, args);
	}
}
