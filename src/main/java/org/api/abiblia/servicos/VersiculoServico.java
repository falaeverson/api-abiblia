package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE;

import java.util.Optional;

import org.api.abiblia.entidades.Versiculo;
import org.api.abiblia.repositorios.VersiculoRepositorio;
import org.api.abiblia.responses.VersiculoResponse;
import org.api.abiblia.util.GenericConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VersiculoServico {

	@Autowired
	private VersiculoRepositorio versiculoRepositorio;

	public Page<Versiculo> versiculos(Versiculo versiculo, Pageable pageable) {
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();

		return versiculoRepositorio.findAll(Example.of(versiculo, matcher), pageable);

	}

	public VersiculoResponse versiculo(Long id) {

		Optional<Versiculo> versao = versiculoRepositorio.findById(id);

		checkThrow(!versao.isPresent(), GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE, id.toString());

		return GenericConvert.convertModelMapper(versao.get(), VersiculoResponse.class);

	}
}