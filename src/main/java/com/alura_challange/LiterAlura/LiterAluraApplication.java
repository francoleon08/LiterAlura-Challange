package com.alura_challange.LiterAlura;

import com.alura_challange.LiterAlura.view.ViewCommandLineInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private ViewCommandLineInterface viewCommandLineInterface;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		viewCommandLineInterface.showMenu();
	}

}
