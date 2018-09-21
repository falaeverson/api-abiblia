package br.com.abiblia.exception;

/**
 * Classe que representa as exceções relacionadas a erros internos do sistema.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */
public class ServerErrorException extends Exception{

	private static final long serialVersionUID = -568937671581368736L;

	public ServerErrorException(ExceptionsMessagesEnum exceptionsMessagesEnum){
          super(exceptionsMessagesEnum);
     }
}