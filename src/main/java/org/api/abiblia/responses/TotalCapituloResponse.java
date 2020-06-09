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
@ApiModel(description = "Classe que representa o total de capítulos dos livros")
public class TotalCapituloResponse implements Serializable {

	private static final long serialVersionUID = -6378187284921831499L;
	
	@ApiModelProperty(value = "Total de capítulos do livro", name = "totalCapitulos", position = 1)
	public Long totalCapitulos;

}