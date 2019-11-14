package org.api.abiblia.responses;

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
@ApiModel(description = "Classe que representa o response de livros")
public class LivroResponse implements Serializable {

	private static final long serialVersionUID = -8053042720343828502L;

	@ApiModelProperty(value = "ID de identificação do livro", name = "id", position = 1)
	public Long id;
	
	@ApiModelProperty(value = "Sequencia do livro na bíblia", name = "posicao", position = 2)
	public Long posicao;

	@ApiModelProperty(value = "ID de identificação do testamento", name = "idTestamento", position = 3)
	public String testamento;

	@ApiModelProperty(value = "Nome do livro", name = "nome", position = 4)
	public String nome;

	@ApiModelProperty(value = "Sigla de identificação do livro", name = "abreviacao", position = 5)
	public String abreviacao;

}