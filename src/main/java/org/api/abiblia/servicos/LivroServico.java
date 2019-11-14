package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.api.abiblia.entidades.Livro;
import org.api.abiblia.repositorios.LivroRepositorio;
import org.api.abiblia.requests.LivroRequest;
import org.api.abiblia.responses.LivroResponse;
import org.api.abiblia.responses.PageLivroResponse;
import org.api.abiblia.util.GenericConvert;
import org.api.abiblia.util.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

@Service
public class LivroServico {

	@Autowired
	private LivroRepositorio livroRepositorio;

	@SuppressWarnings({ "unchecked", "serial" })
	public PageLivroResponse livros(LivroRequest request) {

		Livro livro = GenericConvert.convertModelMapper(request, Livro.class);
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		
		Page<Livro> livros = livroRepositorio.findAll(Example.of(livro, matcher), PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));
		
		PageLivroResponse response = new PageLivroResponse(GenericConvert.convertModelMapperToPageDefault(livros, new TypeToken<List<LivroResponse>>() {}.getType()));

		return response;
	}

	public LivroResponse livro(Long id) {

		Optional<Livro> livro = livroRepositorio.findById(id);

		checkThrow(!livro.isPresent(), GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());

		LivroResponse response = GenericConvert.convertModelMapper(livro.get(), LivroResponse.class);

		return response;
	}
}