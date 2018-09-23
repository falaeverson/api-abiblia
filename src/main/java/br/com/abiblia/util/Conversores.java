package br.com.abiblia.util;

import org.springframework.data.domain.Page;

public abstract class Conversores {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static PageDTO buildPage(Page p) {

		if (p == null) {

			return null;
		} else {

			PageDTO pageApi = new PageDTO();
			pageApi.setContent(p.getContent());
			pageApi.setHasContent(p.hasContent());
			pageApi.setNumber(p.getNumber());
			pageApi.setNumberOfElements(p.getNumberOfElements());
			pageApi.setSize(p.getSize());
			pageApi.setTotalElements(p.getTotalElements());
			pageApi.setTotalPages(p.getTotalPages());
			pageApi.setHasNextPage(p.hasNext());
			pageApi.setHasPreviousPage(p.hasPrevious());
			pageApi.setFirst(p.isFirst());
			pageApi.setLast(p.isLast());
			
			return pageApi;
		}
	}

}
