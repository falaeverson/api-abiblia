package org.api.abiblia.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LivroRequest extends GenericRequest {

	private static final long serialVersionUID = -9222743369303607758L;

	@ApiModelProperty(value = "Descrição do testamento", position = 1)
	private String testamento;

	@ApiModelProperty(value = "Nome do livro", position = 2)
	private String nome;

	@ApiModelProperty(value = "Sigra de identificação do livro", position = 3)
	private String abreviacao;
}