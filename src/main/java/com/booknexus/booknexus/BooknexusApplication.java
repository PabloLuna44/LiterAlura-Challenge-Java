package com.booknexus.booknexus;

import com.booknexus.booknexus.main.Main;
import com.booknexus.booknexus.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class BooknexusApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BooknexusApplication.class, args);
	}

	@Override
	public void run(String... args) throws UnsupportedEncodingException, JsonProcessingException {
		Main main = new Main(repository);
		main.menu();
	}


}
