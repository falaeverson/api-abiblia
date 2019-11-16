package org.api.abiblia.servicos;

import static org.api.abiblia.exception.Exception.checkThrow;
import static org.api.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE;

import java.util.Optional;

import org.api.abiblia.entidades.Versao;
import org.api.abiblia.repositorios.VersaoRepositorio;
import org.api.abiblia.responses.VersaoResponse;
import org.api.abiblia.util.GenericConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VersaoServico {

	@Autowired
	private VersaoRepositorio versaoRepositorio;

	public Page<Versao> versoes(Pageable pageable) {

		 return versaoRepositorio.findAll(pageable);

	}

	public VersaoResponse versao(Long id) {

		Optional<Versao> versao = versaoRepositorio.findById(id);

		checkThrow(!versao.isPresent(), GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE, id.toString());

		return GenericConvert.convertModelMapper(versao.get(), VersaoResponse.class);

	}
}