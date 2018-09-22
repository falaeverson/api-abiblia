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
     
     @ApiModelProperty(value="Limite do registro na solicitação (Default = 50)", name="totalPagina", position=101)
     @Min(1)
     @Max(50)
     private int totalPagina = 50;
     
     @ApiModelProperty(value="Campo para ordenação dos registros (Default = id).", name="campos", position=102)
     private String campos = "id";
     
     @ApiModelProperty(value="Direção da ordenação dos registros (Default = DESC)", name="ordem", position=103)
     private Sort.Direction ordem = Direction.DESC;
     
     public PageRequest pageRequest() {
          
          return PageRequest.of(this.pagina, this.totalPagina, Sort.by(this.ordem, this.campos));
          
     }
}
