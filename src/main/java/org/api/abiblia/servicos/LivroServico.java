package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE;

import java.util.Optional;

import org.api.abiblia.entidades.Livro;
import org.api.abiblia.repositorios.LivroRepositorio;
import org.api.abiblia.responses.LivroResponse;
import org.api.abiblia.util.GenericConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroServico {

	@Autowired
	private LivroRepositorio livroRepositorio;

	public Page<Livro> livros(Livro livro, Pageable pageable) {

		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
		
		return livroRepositorio.findAll(Example.of(livro, matcher), pageable);

	}

	public LivroResponse livro(Long id) {

		Optional<Livro> livro = livroRepositorio.findById(id);

		checkThrow(!livro.isPresent(), GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());

		LivroResponse response = GenericConvert.convertModelMapper(livro.get(), LivroResponse.class);

		return response;
	}
}