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
@ApiModel(description = "Classe que representa o response de versículos")
public class VersiculoResponse implements Serializable {

	private static final long serialVersionUID = -7679538623387774339L;

	@ApiModelProperty(value = "ID de identificação do versículo", name = "id", position = 1)
	public Long id;

	@ApiModelProperty(value = "ID de identificação da versão", name = "idVersao", position = 2)
	public Long idVersao;

	@ApiModelProperty(value = "ID de identificação do livro", name = "idLivro", position = 3)
	public Long idLivro;

	@ApiModelProperty(value = "Número do capítulo", name = "capitulo", position = 4)
	public Integer capitulo;

	@ApiModelProperty(value = "Número do versículo", name = "versiculoNumero", position = 5)
	public Integer numeroVersiculo;

	@ApiModelProperty(value = "Texto do versículo", name = "versiculoTexto", position = 6)
	public String textoVersiculo;

}