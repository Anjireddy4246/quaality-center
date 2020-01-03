package com.ts.codemetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class CodeMetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeMetricsApplication.class, args);
		System.out.println("Code Metrics APpplciation Started");
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
