package br.com.dock.tech.processoseletivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@SpringBootApplication
@RestController
@Hidden
public class ProcessoSeletivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessoSeletivoApplication.class, args);
	}
	
	@RequestMapping("/")
	public String home() {
		return "Hello Docker World!";
	}

}
