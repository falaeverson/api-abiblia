package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE;

import java.util.List;
import java.util.Optional;

import org.api.abiblia.entidades.Versao;
import org.api.abiblia.repositorios.VersaoRepositorio;
import org.api.abiblia.requests.GenericRequest;
import org.api.abiblia.responses.PageVersaoResponse;
import org.api.abiblia.responses.VersaoResponse;
import org.api.abiblia.util.GenericConvert;
import org.api.abiblia.util.PageDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

@Service
public class VersaoServico {

	@Autowired
	private VersaoRepositorio versaoRepositorio;

	@SuppressWarnings({ "unchecked", "serial" })
	public PageVersaoResponse versoes(GenericRequest request) {

		Page<Versao> versoes = versaoRepositorio.findAll(PageDefault.setPageable(request.getPage(),request.getLimit(), request.getCampos(), request.getOrder()));

		return new PageVersaoResponse(GenericConvert.convertModelMapperToPageDefault(versoes, new TypeToken<List<VersaoResponse>>() {}.getType()));

	}

	public VersaoResponse versao(Long id) {

		Optional<Versao> versao = versaoRepositorio.findById(id);

		checkThrow(!versao.isPresent(), GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());

		return GenericConvert.convertModelMapper(versao.get(), VersaoResponse.class);

	}
}