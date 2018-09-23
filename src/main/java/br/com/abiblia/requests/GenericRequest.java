package br.com.abiblia.requests;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.abiblia.util.PageDefault;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericRequest implements Serializable{

     private static final long serialVersionUID = 1143389901093760983L;

     @ApiModelProperty(value="Página solictada (Default = 0)", name="page", position=100)
     @Min(PageDefault.PAGE_DEFAULT)
     private int page = PageDefault.PAGE_DEFAULT;
     
     @ApiModelProperty(value="Limite do registro por página (Default = 50)", name="limit", position=101)
     @Min(1)
     @Max(PageDefault.LIMIT_DEFAULT)
     private int limit = PageDefault.LIMIT_DEFAULT;
     
     @ApiModelProperty(value="Campo para ordenação dos registros (Default = id).", name="campos", position=102)
     private String campos = "id";
     
     @ApiModelProperty(value="Direção da ordenação dos registros (Default = ASC)", name="order", position=103)
     private Sort.Direction order = Direction.ASC;
     
}
