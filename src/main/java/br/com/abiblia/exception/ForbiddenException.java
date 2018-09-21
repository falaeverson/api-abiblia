package br.com.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a solicitações cujo solicitante não tem acesso.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */

public class ForbiddenException extends Exception{

	private static final long serialVersionUID = -6570055256219891233L;

	public ForbiddenException(ExceptionsMessagesEnum exeptionsMessagesEnum){
          super(exeptionsMessagesEnum);
     }

}