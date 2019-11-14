package org.api.abiblia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.api.abiblia")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
