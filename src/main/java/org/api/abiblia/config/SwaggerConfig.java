package org.api.abiblia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("org.api.abiblia")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());

		return docket;
	}

	private ApiInfo apiInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("API aBíblia").description("Api para bíblia").version("1.0")
				.contact(contato());

		return apiInfoBuilder.build();

	}

	private Contact contato() {

		return new Contact("Everson Teixeira", "https://github.com/falaeverson", "tx.everson@gmail.com");

	}
}