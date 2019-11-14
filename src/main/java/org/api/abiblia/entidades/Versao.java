package org.api.abiblia.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "versoes")
public class Versao {

	@Id
	private Long id;

	private String nome;

	private String abreviacao;

}