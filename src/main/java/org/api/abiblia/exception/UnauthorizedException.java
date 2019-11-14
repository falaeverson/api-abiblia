package org.api.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a solicitação de recurso não
 * permitida por validações de regras de negócio..
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */

public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 2664785227218738262L;

	public UnauthorizedException(ExceptionsMessagesEnum exeptionsMessagesEnum) {
		super(exeptionsMessagesEnum);
	}

}