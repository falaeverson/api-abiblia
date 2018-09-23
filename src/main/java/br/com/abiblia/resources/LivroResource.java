package br.com.abiblia.resources;

import static br.com.abiblia.exception.Exception.checkThrow;
import static br.com.abiblia.exception.ExceptionsMessagesEnum.GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.abiblia.requests.LivroRequest;
import br.com.abiblia.responses.LivroResponse;
import br.com.abiblia.responses.PageLivroResponse;
import br.com.abiblia.servicos.LivroServico;
import br.com.abiblia.util.ConstantesRest;
import br.com.twsoftware.alfred.object.Objeto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = ConstantesRest.PATH_LIVRO, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = ConstantesRest.PATH_LIVRO, produces = MediaType.APPLICATION_JSON_VALUE, description = "Livros da Bíblia", tags = {
		"LIVROS" })
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

		PageLivroResponse page = livroServico.livros(request);

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

		checkThrow(Objeto.isBlank(livro), GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE, id.toString());

		return ResponseEntity.ok(livro);

	}

}
