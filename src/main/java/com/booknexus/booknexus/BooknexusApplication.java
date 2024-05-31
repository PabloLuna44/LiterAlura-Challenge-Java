package com.booknexus.booknexus;

import com.booknexus.booknexus.main.Main;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class BooknexusApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BooknexusApplication.class, args);
	}

	@Override
	public void run(String... args) throws UnsupportedEncodingException, JsonProcessingException {
		Main main = new Main();
		main.menu();

	}

}
