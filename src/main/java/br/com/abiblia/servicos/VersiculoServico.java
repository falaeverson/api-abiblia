package br.com.abiblia.servicos;

import static br.com.abiblia.exception.Exception.checkThrow;
import static br.com.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.abiblia.entidades.Versiculo;
import br.com.abiblia.repositorios.VersiculoRepositorio;
import br.com.abiblia.requests.VersiculoRequest;
import br.com.abiblia.responses.PageVersiculoResponse;
import br.com.abiblia.responses.VersiculoResponse;
import br.com.abiblia.util.GenericConvert;
import br.com.abiblia.util.PageDefault;

@Service
public class VersiculoServico {

	@Autowired
	private VersiculoRepositorio versiculoRepositorio;

	@SuppressWarnings({ "unchecked", "serial" })
	public PageVersiculoResponse versiculos(VersiculoRequest request) {

		Versiculo versiculo = GenericConvert.convertModelMapper(request, Versiculo.class);
		
		Page<Versiculo> pageVersiculos = versiculoRepositorio.findAll(Example.of(versiculo), PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));
		
		return new PageVersiculoResponse(GenericConvert.convertModelMapperToPagePIER(pageVersiculos, new TypeToken<List<Versiculo>>(){}.getType()));
		
	}

	public VersiculoResponse versiculo(Long id) {

		Optional<Versiculo> versao = versiculoRepositorio.findById(id);

		checkThrow( !versao.isPresent() , GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE, id.toString());
		
		return GenericConvert.convertModelMapper(versao.get(), VersiculoResponse.class);
		
	}
}
