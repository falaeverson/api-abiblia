
package br.com.abiblia.exception;

import static org.springframework.http.HttpStatus.*;

import br.com.twsoftware.alfred.object.Objeto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public enum ExceptionsMessagesEnum {
	
	// Exemplos
	GLOBAL_ERRO_SERVER(INTERNAL_SERVER_ERROR.value(), "{}", ServerErrorException.class),
	GLOBAL_ERRO_BAD_REQUEST(BAD_REQUEST.value(), "{}", BadRequestException.class),
	GLOBAL_ERRO_NOT_FOUND(NOT_FOUND.value(), "{}", NotFoundException.class),
	GLOBAL_ERRO_UNAUTHORIZED(UNAUTHORIZED.value(), "{}", UnauthorizedException.class),
	GLOBAL_ERRO_NOT_IMPLEMENTED(NOT_IMPLEMENTED.value(), "{}", NotImplementedException.class),
	GLOBAL_ERRO_FORBINDEEN(FORBIDDEN.value(), "{}", ForbiddenException.class),

	// Mensagens
	GLOBAL_RECURSO_NAO_DISPONIVEL(NOT_FOUND.value(), "Recurso não disponível", NotFoundException.class),
	GLOBAL_BASE_NAO_DISPONIVEL(NOT_FOUND.value(), "Base não disponível", NotFoundException.class),
	GLOBAL_NOT_FOUND_LIVRO_NAO_EXISTE(NOT_FOUND.value(), "O livro solicitado não existe. ID: {}", NotFoundException.class),
	GLOBAL_NOT_FOUND_VERSICULO_NAO_EXISTE(NOT_FOUND.value(), "O versículo solicitado não existe. ID: {}", NotFoundException.class),
	GLOBAL_NOT_FOUND_VERSAO_NAO_EXISTE(NOT_FOUND.value(), "A versão solicitado não existe. ID: {}", NotFoundException.class);
	
	@Getter
	private Integer httpCode;

	@Getter
	@Setter
	private String message;

	private String defaultMessage;

	@Getter
	private Class<? extends Exception> klass;

	ExceptionsMessagesEnum(int httpCode, String defaultMessage, Class<? extends Exception> klass) {
		this.httpCode = httpCode;
		this.defaultMessage = defaultMessage;
		this.klass = klass;
		this.message = Objeto.isBlank(this.message) ? this.defaultMessage.replace("{}", "") : this.message;
	}

	/**
	 * 
	 * Método responsável pelo disparo da exception
	 * 
	 */
	public void raise() {

		log.debug("Raising error: {}", this);

		this.message = Objeto.isBlank(this.message) ? this.defaultMessage.replace("{}", "") : this.message;

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

		if (textoDinamico != null && textoDinamico.length > 0) {

			Integer count = 0;
			String mensagemBase = this.defaultMessage;
			while (mensagemBase.contains("{}")) {

				if (textoDinamico.length == 1) {

					this.message = this.defaultMessage.replace("{}", textoDinamico[count]);
					mensagemBase = this.message;
				} else {

					this.message = this.defaultMessage.replaceFirst("\\{\\}", textoDinamico[count]);
					mensagemBase = this.message;
				}
				count++;
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

		return this.httpCode == BAD_REQUEST.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 401.
	 * 
	 */
	private Boolean unauthorized() {

		return this.httpCode == UNAUTHORIZED.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 403.
	 * 
	 */
	private Boolean forbidden() {

		return this.httpCode == FORBIDDEN.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 404.
	 * 
	 */
	private Boolean notFound() {

		return this.httpCode == NOT_FOUND.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro com código 428.
	 * 
	 */
	private Boolean preconditionRequired() {

		return this.httpCode == PRECONDITION_REQUIRED.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro comcódigo 500.
	 * 
	 */
	private Boolean serverError() {

		return this.httpCode == INTERNAL_SERVER_ERROR.value();
	}

	/**
	 * 
	 * Método responsável pela validação dos códigos de erro comcódigo 501.
	 * 
	 */
	private Boolean notImplemented() {

		return this.httpCode == NOT_IMPLEMENTED.value();
	}

}