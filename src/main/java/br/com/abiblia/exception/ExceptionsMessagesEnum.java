
package br.com.abiblia.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;
import static org.springframework.http.HttpStatus.PRECONDITION_REQUIRED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import br.com.twsoftware.alfred.object.Objeto;
import lombok.Getter;

/**
 * <b>Os Erros podem ser das seguintes naturezas:</b><br/>
 * <br/>
 * 
 * <b>BAD_REQUEST = 400 </b> <br/>
 * Solicitação de recurso não permitida por validações de regras de negócio.
 * <br/>
 * <b>UNAUTHORIZED = 401 </b> <br/>
 * Solicitação de recurso não permitida para usuário sem acesso a determinada
 * funcionalidade. (Bloqueio parcial ao PIER) <br/>
 * <b>FORBIDDEN = 403 </b> <br/>
 * Acesso não permitido. <br/>
 * <b>NOT_FOUND = 404 </b> <br/>
 * Recurso não encontrado. <br/>
 * <b>PRECONDITION_REQUIRED = 428 </b> <br/>
 * Solicitação de recurso que requer antes a execução de outro. <br/>
 * <b>INTERNAL_SERVER_ERROR = 500 </b> <br/>
 * Erro interno no servidor. (Demais erros não previstos deverão ser
 * interpretados com essa exceção) <br/>
 * <b>NOT_IMPLEMENTED = 501 </b> <br/>
 * Recurso ainda não implementado por completo. (Deve ser usado de forma
 * temporária). <br/>
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 */

public enum ExceptionsMessagesEnum {
	
	// Exemplos
	GLOBAL_ERRO_SERVER(INTERNAL_SERVER_ERROR.value(), "{}", ServerErrorException.class),
	GLOBAL_ERRO_BAD_REQUEST(BAD_REQUEST.value(), "{}", BadRequestException.class),
	GLOBAL_ERRO_NOT_FOUND(NOT_FOUND.value(), "{}", NotFoundException.class),
	GLOBAL_ERRO_UNAUTHORIZED(UNAUTHORIZED.value(), "{}", UnauthorizedException.class),
	GLOBAL_ERRO_NOT_IMPLEMENTED(NOT_IMPLEMENTED.value(), "{}", NotImplementedException.class),
	GLOBAL_ERRO_FORBINDEEN(FORBIDDEN.value(), "{}", ForbiddenException.class),

	// Mensagens
	GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE(NOT_FOUND.value(), "O livro solicitado não existe. ID: {}", NotFoundException.class),
	
	;
	
	@Getter
	private Integer codigo;

	@Getter
	private String mensagem;

	private String mensagemPadrao;

	@Getter
	private Class<? extends Exception> klass;

	ExceptionsMessagesEnum(int codigo, String mensagem, Class<? extends Exception> klass) {
		this.codigo = codigo;
		this.mensagemPadrao = mensagem;
		this.klass = klass;
		this.mensagem = Objeto.isBlank(this.mensagem) ? this.mensagemPadrao.replace("{}", "") : this.mensagem;
	}

	/**
	 * 
	 * Método responsável pelo disparo da exception
	 * 
	 */
	public void raise() {

		System.out.println("Raising error: {}");

		this.mensagem = Objeto.isBlank(this.mensagem) ? this.mensagemPadrao.replace("{}", "") : this.mensagem;

		if (this.badRequest()) {

			throw new BadRequestException(this);
			
		} else if (this.unauthorized()) {

			throw new UnauthorizedException(this);
			
		} else if (this.forbidden()) {

			throw new ForbiddenException(this);
			
		} else if (this.notFound()) {

			throw new NotFoundException(this);
			
		} else if (this.preconditionRequired()) {

			throw new PreconditionRequiredException(this);
			
		} else if (this.serverError()) {

			throw new ServerErrorException(this);
			
		} else if (this.notImplemented()) {

			throw new NotImplementedException(this);
			
		}

	}

	/**
	 * 
	 * Método responsável pelo disparo da exception com inclusão de mensagem
	 * personalizada total ou parcial.
	 * 
	 * @param textoDinamico Esse parametro irá substituir os simbolos {} incluidos
	 *                      na mensagem respectivamente. Caso seja informado mais de
	 *                      um simbolo {} e apenas uma passagem de parâmetro, o
	 *                      mesmo irá substituir todas as chaves para o parâmetro
	 *                      informado.
	 * 
	 * @author Everson Teixeira <tx.everson@gmail.com>
	 * 
	 */
	public void raise(String... textoDinamico) {

		if (Objeto.notBlank(textoDinamico)) {

			Integer count = 0;
			String mensagemBase = this.mensagemPadrao;
			while (mensagemBase.contains("{}")) {

				if (textoDinamico.length == 1) {

					this.mensagem = this.mensagemPadrao.replace("{}", textoDinamico[count]);
					mensagemBase = this.mensagem;
				} else {

					this.mensagem = this.mensagemPadrao.replaceFirst("\\{\\}", textoDinamico[count]);
					mensagemBase = this.mensagem;
				}
				count++;
			}
		}
		raise();
	}

	/**
	 * 
	 * Método responsável pelo disparo da exception com log de erro.
	 * 
	 * @param textoErro Texto a ser impresso no log
	 * 
	 * @author Everson Teixeira <tx.everson@gmail.com>
	 * 
	 */
	public void raiseLogError(String... textoErro) {

		if (Objeto.notBlank(textoErro)) {

			for (String erro : textoErro) {
				System.out.println(erro);
			}
		}

		raise();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 400.
	 * 
	 */
	private Boolean badRequest() {

		return this.codigo == BAD_REQUEST.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 401.
	 * 
	 */
	private Boolean unauthorized() {

		return this.codigo == UNAUTHORIZED.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 403.
	 * 
	 */
	private Boolean forbidden() {

		return this.codigo == FORBIDDEN.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 404.
	 * 
	 */
	private Boolean notFound() {

		return this.codigo == NOT_FOUND.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 428.
	 * 
	 */
	private Boolean preconditionRequired() {

		return this.codigo == PRECONDITION_REQUIRED.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro comcódigo 500.
	 * 
	 */
	private Boolean serverError() {

		return this.codigo == INTERNAL_SERVER_ERROR.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro comcódigo 501.
	 * 
	 */
	private Boolean notImplemented() {

		return this.codigo == NOT_IMPLEMENTED.value();
	}

}