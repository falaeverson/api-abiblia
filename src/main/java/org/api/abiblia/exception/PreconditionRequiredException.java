package org.api.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a solicitações de recurso que
 * requer antes a execução de outro.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */
public class PreconditionRequiredException extends Exception {

	private static final long serialVersionUID = -2078371199704208359L;

	public PreconditionRequiredException(ExceptionsMessagesEnum exeptionsMessagesEnum) {
		super(exeptionsMessagesEnum);
	}

}