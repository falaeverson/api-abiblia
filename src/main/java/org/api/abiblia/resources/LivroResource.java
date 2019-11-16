package org.api.abiblia.resources;

import java.util.List;

import org.api.abiblia.entidades.Livro;
import org.api.abiblia.requests.LivroRequest;
import org.api.abiblia.responses.LivroResponse;
import org.api.abiblia.responses.PageLivroResponse;
import org.api.abiblia.servicos.LivroServico;
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
@RequestMapping(value = ConstantesRest.PATH_LIVRO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantesRest.PATH_LIVRO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Livros da Bíblia", tags = { "LIVROS" })
public class LivroResource {

	@Autowired
	private LivroServico livroServico;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Lista os livros", notes = "Recurso para listagem dos livros da Bíblia", response = PageLivroResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> livros(@ModelAttribute(value = "LivroRequest") LivroRequest request) {

		Livro livro = GenericConvert.convertModelMapper(request, Livro.class);
		
		Page<Livro> livros = livroServico.livros(livro, PageDefault.setPageable(request.getPage(), request.getLimit(), request.getCampos(), request.getOrder()));
		
		@SuppressWarnings({ "unchecked", "serial" })
		PageLivroResponse page = new PageLivroResponse(GenericConvert.convertModelMapperToPageDefault(livros, new TypeToken<List<LivroResponse>>() {}.getType()));

		return ResponseEntity.ok(page);

	}

	@ResponseBody
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Consulta o livro por id", notes = "Recurso para consulta de livros da Bíblia", response = LivroResponse.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Requisição efetuada com sucesso!."),
			@ApiResponse(code = 400, message = "Requisição inválida."),
			@ApiResponse(code = 404, message = "Recurso não encontrado."),
			@ApiResponse(code = 500, message = "Erro interno do sistema.") })
	public ResponseEntity<?> livro(@PathVariable("id") Long id) {

		LivroResponse livro = livroServico.livro(id);

		return ResponseEntity.ok(livro);

	}
}
