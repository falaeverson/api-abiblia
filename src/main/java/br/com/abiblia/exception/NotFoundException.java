package br.com.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a busca de recursos inexistentes.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 2373086209997119433L;

	public NotFoundException(ExceptionsMessagesEnum exeptionsMessagesEnum){
          super(exeptionsMessagesEnum);
     }
}