package br.com.abiblia.requests;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericRequest implements Serializable{

     private static final long serialVersionUID = 1143389901093760983L;

     @ApiModelProperty(value="Pagina solictada (Default = 0)", name="pagina", position=100)
     @Min(0)
     private int pagina = 0;
     
     @ApiModelProperty(value="Limite do registro na solicitação (Default = 100)", name="totalPagina", position=101)
     @Max(100)
     private int totalPagina = 100;
     
     @ApiModelProperty(value="Campo para ordenação dos registros (Default = id).", name="campo", position=102)
     private String campo = "id";
     
     @ApiModelProperty(value="Direção da ordenação dos registros (Default = DESC)", name="ordem", position=103)
     private Sort.Direction ordem = Direction.DESC;
     
     public PageRequest pageRequest() {
          
          return PageRequest.of(this.pagina, this.totalPagina, Sort.by(this.ordem, this.campo));
          
     }
}
