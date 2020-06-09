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
@ApiModel(description = "Classe que representa o total de versículos dos capítulos")
public class TotalVersiculoResponse implements Serializable {
	private static final long serialVersionUID = 8298594180643571598L;
	
	@ApiModelProperty(value = "Total de versículos do capítulo", name = "totalVersiculos", position = 1)
	public Long totalVersiculos;

}