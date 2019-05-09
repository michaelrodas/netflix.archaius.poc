package com.ntflxarch.pocna;

import com.ntflxarch.pocna.controller.LocalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class PocnaApplication {

	@Autowired
	private LocalTest myTest;

	public static void main(String[] args) {
		SpringApplication.run(PocnaApplication.class, args);
	}

	@Bean
	public LocalTest getLocalTest(){
		return new LocalTest();
	}

	@PostConstruct
	private void init() {
		myTest.testLocally();
	}

}
