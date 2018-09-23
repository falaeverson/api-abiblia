package br.com.abiblia.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LivroRequest extends GenericRequest {

	private static final long serialVersionUID = -8590881733592240522L;

	@ApiModelProperty(value = "Identificado do versículo", name = "id", position = 1)
	private Long id;

	@ApiModelProperty(value = "Identificado do testamento", name = "idTestamento", position = 2)
	private Long idTestamento;

	@ApiModelProperty(value = "Sequencia do livro na bíblia", name = "posicao", position = 3)
	private Long posicao;

	@ApiModelProperty(value = "Nome do livro", name = "nome", position = 4)
	private String nome;

	@ApiModelProperty(value = "Sigla de identificação do livro", name = "abreviacao", position = 5)
	private String abreviacao;

}
