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
@ApiModel(description = "Classe que representa o response de livros")
public class LivroResponse implements Serializable {

	private static final long serialVersionUID = -8053042720343828502L;

	@ApiModelProperty(value="ID de identificação do livro", name="", position = 1)
	public Integer id;

	@ApiModelProperty(value="ID de identificação do testamento", name="", position = 2)
	public Integer idTestamento;
	
	@ApiModelProperty(value="Sequencia do livro na bíblia", name="", position = 4)
	public Integer posicao;
	
	@ApiModelProperty(value="Nome do livro", name="nome", position = 5)
	public String nome;

	@ApiModelProperty(value="Sigla de identificaç;ao do livro", name="sigla", position = 6)
	public String abreviacao;

}
