package br.com.abiblia.servicos;

import static br.com.abiblia.exception.Exception.checkThrow;
import static br.com.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.abiblia.entidades.Versao;
import br.com.abiblia.repositorios.VersaoRepositorio;
import br.com.abiblia.requests.VersaoRequest;
import br.com.abiblia.responses.PageVersaoResponse;
import br.com.abiblia.responses.VersaoResponse;
import br.com.abiblia.util.GenericConvert;
import br.com.abiblia.util.PageDefault;

@Service
public class VersaoServico {

	@Autowired
	private VersaoRepositorio versaoRepositorio;

	@SuppressWarnings({ "unchecked", "serial" })
	public PageVersaoResponse versoes(VersaoRequest request) {

		Versao versao = GenericConvert.convertModelMapper(request, Versao.class);
		
		Page<Versao> versoes = versaoRepositorio.findAll(Example.of(versao), PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));

		return new PageVersaoResponse(GenericConvert.convertModelMapperToPageDefault(versoes, new TypeToken<List<VersaoResponse>>(){}.getType()));
		
	}

	public VersaoResponse versao(Long id) {

		Optional<Versao> versao = versaoRepositorio.findById(id);
		
		checkThrow( !versao.isPresent() , GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());
		
		return GenericConvert.convertModelMapper(versao.get(), VersaoResponse.class);
		
	}
}
