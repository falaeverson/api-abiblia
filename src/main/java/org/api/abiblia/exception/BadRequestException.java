package org.api.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a solicitações não permitidas.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */
public class BadRequestException extends Exception {

	private static final long serialVersionUID = -5219946567382594359L;

	public BadRequestException(ExceptionsMessagesEnum exeptionsMessagesEnum) {
		
		super(exeptionsMessagesEnum);
		
	}
	
}
