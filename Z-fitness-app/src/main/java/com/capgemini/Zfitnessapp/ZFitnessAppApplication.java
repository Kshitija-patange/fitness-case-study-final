package com.capgemini.Zfitnessapp;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.function.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication(scanBasePackages = "com.capgemini.fitness")
@EntityScan(basePackages = "com.capgemini.fitness.entity")
@EnableJpaRepositories(basePackages = "com.capgemini.fitness.dao")
@EnableOpenApi
public class ZFitnessAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZFitnessAppApplication.class, args);

	}

	@Bean
	public Docket openApiUserFitnessApp() {
		return new Docket(DocumentationType.OAS_30)
				.groupName("open-api-fitness-app")
				.select()
				.paths(fitnessPaths())
				.build();
	}

	private Predicate<String> fitnessPaths() {
		return regex(".*/fitness/.*");
	}
}
