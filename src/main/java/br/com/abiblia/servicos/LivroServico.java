package br.com.abiblia.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import static br.com.abiblia.exception.Exception.checkThrow;
import static br.com.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE;

import br.com.abiblia.entidades.Livro;
import br.com.abiblia.repositorios.LivroRepositorio;
import br.com.abiblia.responses.LivroResponse;
import br.com.abiblia.responses.PageLivroResponse;
import br.com.abiblia.util.GenericConvert;

@Service
public class LivroServico {

	@Autowired
	private LivroRepositorio livroRepositorio;

	@SuppressWarnings({ "serial", "unchecked" })
	public PageLivroResponse livros(PageRequest pageRequest) {

		Page<Livro> livros = livroRepositorio.findAll(pageRequest);

		PageLivroResponse response = 
				new PageLivroResponse(GenericConvert.convertModelMapperToPagePIER(livros, new TypeToken<List<Livro>>(){}.getType()));
		
		return response;
	}

	public LivroResponse livro(Long id) {

		Optional<Livro> livro = livroRepositorio.findById(id);

		checkThrow( !livro.isPresent() , GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());
		
		LivroResponse response = GenericConvert.convertModelMapper(livro.get(), LivroResponse.class);
		
		return response;
	}
}
