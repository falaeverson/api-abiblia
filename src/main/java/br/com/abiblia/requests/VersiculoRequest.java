package br.com.abiblia.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VersiculoRequest extends GenericRequest{

     private static final long serialVersionUID = 4936002668463837663L;

     @ApiModelProperty(value="Identificado do versiculo", name="id", position=1)
     private Long id;

     @ApiModelProperty(value="Identificado da versão", name="idVersao", position=2)
     private Long idVersao;
     
     @ApiModelProperty(value="Identificado do testamento", name="idTestamento", position=2)
     private Long idTestamento;
     
     @ApiModelProperty(value="Identificado do livro", name="idLivro", position=3)
     private Long idLivro;
}
