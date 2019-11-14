package org.api.abiblia.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class VersiculoRequest extends GenericRequest {

	private static final long serialVersionUID = 4936002668463837663L;

	@ApiModelProperty(value = "Identificado da versão", position = 1)
	private Long idVersao;

	@ApiModelProperty(value = "Identificado do livro", position = 2)
	private Long idLivro;

	@ApiModelProperty(value = "Identificado do capítulo do livro", position = 3)
	private Integer capitulo;
	
	@ApiModelProperty(value = "Texto para pesquisa do versículo", position = 4)
	private String textoVersiculo;
}