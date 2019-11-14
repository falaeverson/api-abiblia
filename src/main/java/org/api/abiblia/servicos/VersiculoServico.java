package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.api.abiblia.entidades.Versiculo;
import org.api.abiblia.repositorios.VersiculoRepositorio;
import org.api.abiblia.requests.VersiculoRequest;
import org.api.abiblia.responses.PageVersiculoResponse;
import org.api.abiblia.responses.VersiculoResponse;
import org.api.abiblia.util.GenericConvert;
import org.api.abiblia.util.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

@Service
public class VersiculoServico {

	@Autowired
	private VersiculoRepositorio versiculoRepositorio;

	@SuppressWarnings({ "unchecked", "serial" })
	public PageVersiculoResponse versiculos(VersiculoRequest request) {

		Versiculo versiculo = GenericConvert.convertModelMapper(request, Versiculo.class);
		
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();

		Page<Versiculo> pageVersiculos = versiculoRepositorio.findAll(Example.of(versiculo, matcher), PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));

		return new PageVersiculoResponse(GenericConvert.convertModelMapperToPageDefault(pageVersiculos, new TypeToken<List<VersiculoResponse>>() {}.getType()));
		
	}

	public VersiculoResponse versiculo(Long id) {

		Optional<Versiculo> versao = versiculoRepositorio.findById(id);

		checkThrow(!versao.isPresent(), GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE, id.toString());

		return GenericConvert.convertModelMapper(versao.get(), VersiculoResponse.class);

	}
}