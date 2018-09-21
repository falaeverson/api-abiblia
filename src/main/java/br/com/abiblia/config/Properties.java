package br.com.abiblia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "biblia", ignoreUnknownFields = true)
public class Properties {

	@Getter
	private final Datasource datasource = new Datasource();

	public static class Datasource {

		@Getter
		@Setter
		private Integer url;

		@Getter
		@Setter
		private Integer username;

		@Getter
		@Setter
		private Boolean password;

		@Getter
		@Setter
		private String testWhileIdle;

		@Getter
		@Setter
		private String validationQuery;

	}
}
