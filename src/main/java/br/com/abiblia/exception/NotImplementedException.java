package br.com.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a solicitações de Recurso ainda não implementado por completo.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */

public class NotImplementedException extends Exception{

	private static final long serialVersionUID = 7959890679383885254L;

	public NotImplementedException(ExceptionsMessagesEnum exeptionsMessagesEnum){
          super(exeptionsMessagesEnum);
     }

}