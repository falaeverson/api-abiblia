package org.api.abiblia.resources;

import java.util.List;

import org.api.abiblia.entidades.Versao;
import org.api.abiblia.requests.GenericRequest;
import org.api.abiblia.responses.LivroResponse;
import org.api.abiblia.responses.PageVersaoResponse;
import org.api.abiblia.responses.TotalCapituloResponse;
import org.api.abiblia.responses.TotalVersiculoResponse;
import org.api.abiblia.responses.VersaoResponse;
import org.api.abiblia.servicos.VersaoServico;
import org.api.abiblia.util.ConstantesRest;
import org.api.abiblia.util.GenericConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ConstantesRest.PATH_VERSAO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantesRest.PATH_VERSAO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Versões da Bíblia", tags = { "VERSOES" })
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
	public ResponseEntity<?> listarVersoes(@ModelAttribute(value = "GenericRequest") GenericRequest request) {

		Page<Versao> versoes = varsaoServico.versoes(request.pageable());

		@SuppressWarnings({ "serial", "unchecked" })
		PageVersaoResponse page = new PageVersaoResponse(GenericConvert.convertModelMapperToPageDefault(versoes, new TypeToken<List<VersaoResponse>>() {}.getType()));
		
		return ResponseEntity.ok(page);

	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta uma versão da Bíblia", notes = "Recurso para consultar uma versão da Bíblia", response = VersaoResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> consultarVersoes(@PathVariable("id") Long id) {

		VersaoResponse versao = varsaoServico.versao(id);

		return ResponseEntity.ok(versao);

	}
	
	@ResponseBody
	@RequestMapping(value = "{id}/livros/{idLivro}/capitulos", method = RequestMethod.GET)
	@ApiOperation(value = "Consultar total de capítulos pelo id do livro", notes = "Recurso para consultar a lista de capítulos do livro", response = LivroResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
		@ApiResponse(code = 400, message = "Requisição inválida."),
		@ApiResponse(code = 404, message = "Recurso não encontrado."),
		@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> capitulos(@PathVariable("id") Long id, @PathVariable("idLivro") Long idLivro) {
		
		TotalCapituloResponse response = varsaoServico.totalCapitulos(id, idLivro);
		
		return ResponseEntity.ok(response);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "{id}/livros/{idLivro}/capitulos/{capitulo}/versiculos", method = RequestMethod.GET)
	@ApiOperation(value = "Consultar total de capítulos pelo id do livro", notes = "Recurso para consultar a lista de capítulos do livro", response = LivroResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
		@ApiResponse(code = 400, message = "Requisição inválida."),
		@ApiResponse(code = 404, message = "Recurso não encontrado."),
		@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> versiculos(@PathVariable("id") Long id, @PathVariable("idLivro") Long idLivro, @PathVariable("capitulo") Integer capitulo) {
		
		TotalVersiculoResponse response = varsaoServico.totalVersiculos(id, idLivro, capitulo);
		
		return ResponseEntity.ok(response);
		
	}

}