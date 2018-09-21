package br.com.abiblia.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "biblia", ignoreUnknownFields = true)
public class AbibliaProperties {

	@Getter
	private final Teste teste = new Teste();

	public static class Teste {

		@Getter
		@Setter
		private String nome;


	}
}
