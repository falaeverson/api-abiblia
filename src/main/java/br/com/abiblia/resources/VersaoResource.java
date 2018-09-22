package br.com.abiblia.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abiblia.entidades.Versao;
import br.com.abiblia.requests.GenericRequest;
import br.com.abiblia.responses.PageVersaoResponse;
import br.com.abiblia.responses.VersaoResponse;
import br.com.abiblia.servicos.VersaoServico;
import br.com.abiblia.util.ConstantesRest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ConstantesRest.PATH_VERSAO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantesRest.PATH_VERSAO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Versões da Bíblia", tags = {
		"VERSOES" })
public class VersaoResource {

	@Autowired
	private VersaoServico varsaoServico;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Lista as versões da Bíblia", notes = "Recurso para listagem das versões da Bíblia", response = PageVersaoResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> versoes(@ModelAttribute(value = "GenericRequest") GenericRequest request) {

		PageRequest pageRequest = request.pageRequest();

		PageVersaoResponse page = varsaoServico.versoes(pageRequest);

		return ResponseEntity.ok(page);

	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta uma versão da Bíblia", notes = "Recurso para consultar uma versão da Bíblia", response = VersaoResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> varsao(@PathVariable("id") Long id) {

		VersaoResponse versao = varsaoServico.versao(id);

		return ResponseEntity.ok(versao);

	}

}
