package org.api.abiblia.entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "versiculos")
@AllArgsConstructor
@NoArgsConstructor
public class Versiculo { 

	@Id
	private Long id;

	private Long idVersao;

	private Long idLivro;

	private Integer capitulo;

	private Integer numeroVersiculo;

	private String textoVersiculo;
	
}