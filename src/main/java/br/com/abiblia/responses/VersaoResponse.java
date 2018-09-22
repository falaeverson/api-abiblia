package br.com.abiblia.responses;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Classe que representa o response de versões")
public class VersaoResponse implements Serializable {

	private static final long serialVersionUID = -8053042720343828502L;

	@ApiModelProperty(value="ID de identificação da versão", name="id", position = 1)
	public Integer id;
	
	@ApiModelProperty(value="Nome da versão", name="nome", position = 2)
	public String nome;

	@ApiModelProperty(value="Sigla de identificação da versão", name="abreviacao", position = 3)
	public String abreviacao;

}
