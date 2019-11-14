package org.api.abiblia.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "livros")
public class Livro {

	@Id
	private Long id;

	private Long posicao;

	private String testamento;

	private String nome;

	private String abreviacao;
	
}