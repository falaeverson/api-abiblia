package org.api.abiblia.resources;

import java.util.List;

import org.api.abiblia.entidades.Versiculo;
import org.api.abiblia.requests.VersiculoRequest;
import org.api.abiblia.responses.PageVersiculoResponse;
import org.api.abiblia.responses.VersiculoResponse;
import org.api.abiblia.servicos.VersiculoServico;
import org.api.abiblia.util.ConstantesRest;
import org.api.abiblia.util.GenericConvert;
import org.api.abiblia.util.PageDefault;
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
@RequestMapping(value = ConstantesRest.PATH_VERSICULO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantesRest.PATH_VERSICULO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Versículos de todos os livros da Bíblia", tags = { "VERSICULO" })
public class VersiculoResource {

	@Autowired
	private VersiculoServico versiculoServico;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Lista os versiculos da Bíblia", notes = "Recurso para listagem de todos os versiculos da Bíblia", response = PageVersiculoResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> listarVersiculos(@ModelAttribute(value = "VersiculoRequest") VersiculoRequest request) {

		Versiculo versiculo = GenericConvert.convertModelMapper(request, Versiculo.class);
		
		Page<Versiculo> versiculos = versiculoServico.versiculos(versiculo, PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));

		@SuppressWarnings({ "serial", "unchecked" })
		PageVersiculoResponse page = new PageVersiculoResponse(GenericConvert.convertModelMapperToPageDefault(versiculos, new TypeToken<List<VersiculoResponse>>() {}.getType()));
		
		return ResponseEntity.ok(page);

	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta um versiculo da Bíblia", notes = "Recurso para consultar versiculos da Bíblia", response = VersiculoResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> consultarVersiculos(@PathVariable("id") Long id) {

		VersiculoResponse versiculo = versiculoServico.versiculo(id);

		return ResponseEntity.ok(versiculo);

	}

}