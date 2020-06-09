package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.api.abiblia.entidades.Versao;
import org.api.abiblia.entidades.Versiculo;
import org.api.abiblia.repositorios.LivroRepositorio;
import org.api.abiblia.repositorios.VersaoRepositorio;
import org.api.abiblia.repositorios.VersiculoRepositorio;
import org.api.abiblia.responses.TotalCapituloResponse;
import org.api.abiblia.responses.TotalVersiculoResponse;
import org.api.abiblia.responses.VersaoResponse;
import org.api.abiblia.util.GenericConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VersaoServico {

	@Autowired
	private VersaoRepositorio versaoRepositorio;

	@Autowired
	private VersiculoRepositorio versiculoRepositorio;

	@Autowired
	private LivroRepositorio livroRepositorio;

	public Page<Versao> versoes(Pageable pageable) {

		return versaoRepositorio.findAll(pageable);

	}

	public VersaoResponse versao(Long id) {

		Optional<Versao> versao = versaoRepositorio.findById(id);

		checkThrow(!versao.isPresent(), GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());

		return GenericConvert.convertModelMapper(versao.get(), VersaoResponse.class);

	}

	public TotalCapituloResponse totalCapitulos(Long id, Long idLivro) {

		checkThrow(!versaoRepositorio.existsById(id), GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());

		checkThrow(!livroRepositorio.existsById(idLivro), GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());

		Versiculo versiculo = new Versiculo();
		versiculo.setIdVersao(id);
		versiculo.setIdLivro(idLivro);
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();

		List<Versiculo> versiculos = versiculoRepositorio.findAll(Example.of(versiculo, matcher));

		Long count = 0L;

		for (Versiculo v : versiculos) {
			count = v.getCapitulo().longValue();
		}

		TotalCapituloResponse resp = new TotalCapituloResponse();
		resp.setTotalCapitulos(count);

		return resp;
	}

	public TotalVersiculoResponse totalVersiculos(Long id, Long idLivro, Integer capitulo) {

		checkThrow(!versaoRepositorio.existsById(id), GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());

		checkThrow(!livroRepositorio.existsById(idLivro), GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());

		Versiculo versiculo = new Versiculo();
		versiculo.setIdVersao(id);
		versiculo.setIdLivro(idLivro);
		versiculo.setCapitulo(capitulo);
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();

		Long count = versiculoRepositorio.count(Example.of(versiculo, matcher));

		TotalVersiculoResponse resp = new TotalVersiculoResponse();
		resp.setTotalVersiculos(count);
		return resp;

	}

}