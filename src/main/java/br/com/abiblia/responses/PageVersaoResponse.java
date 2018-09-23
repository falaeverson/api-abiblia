package br.com.abiblia.responses;

import java.io.Serializable;

import br.com.abiblia.util.PageDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description="Paginação de livros")
public class PageVersaoResponse extends PageDTO<VersaoResponse> implements Serializable {

	private static final long serialVersionUID = -5873252259143195332L;

	public PageVersaoResponse(PageDTO<VersaoResponse> p) {
		super(p.getNumber(), p.size, p.totalPages, p.numberOfElements, p.totalElements, p.firstPage, p.hasPreviousPage, p.hasNextPage, p.hasContent,
				p.first, p.last, p.nextPage, p.previousPage, p.content);
	}

}
