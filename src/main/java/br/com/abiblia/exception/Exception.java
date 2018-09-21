
package br.com.abiblia.exception;

import lombok.Getter;

/**
 * Classe que representa as exceções personalizadas do PIER.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 */
public class Exception extends RuntimeException {

	private static final long serialVersionUID = -601362897548358062L;

	@Getter
	private ExceptionsMessagesEnum msgEnum;

	public Exception(ExceptionsMessagesEnum exeptionsMessagesEnum) {

		super(exeptionsMessagesEnum.getMessage());
		
		this.msgEnum = exeptionsMessagesEnum;

	}

	public static void checkThrow(boolean expression, ExceptionsMessagesEnum ExceptionsMessagesEnum) throws Exception {

		if (expression) {
			ExceptionsMessagesEnum.raise();
		}
	}

	public static void checkThrow(boolean expression, ExceptionsMessagesEnum ExceptionsMessagesEnum, String... textoDinamico) throws Exception {

		if (expression) {
			ExceptionsMessagesEnum.raise(textoDinamico);
		}
	}

}