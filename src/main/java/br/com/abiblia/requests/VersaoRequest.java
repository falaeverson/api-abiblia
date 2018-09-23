package br.com.abiblia.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VersaoRequest extends GenericRequest {

	private static final long serialVersionUID = 8562466018358591900L;

	@ApiModelProperty(value = "Identificado da versão", name = "id", position = 1)
	private Long id;

	@ApiModelProperty(value = "Nome da versão", name = "nome", position = 2)
	private String nome;

	@ApiModelProperty(value = "Sigla de identificação da versão", name = "abreviacao", position = 3)
	private String abreviacao;

}
