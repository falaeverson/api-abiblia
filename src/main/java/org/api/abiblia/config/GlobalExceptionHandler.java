package org.api.abiblia.config;

import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.api.abiblia.exception.BadRequestException;
import org.api.abiblia.exception.Exception;
import org.api.abiblia.exception.ExceptionsMessagesEnum;
import org.api.abiblia.exception.ForbiddenException;
import org.api.abiblia.exception.NotFoundException;
import org.api.abiblia.exception.NotImplementedException;
import org.api.abiblia.exception.PreconditionRequiredException;
import org.api.abiblia.exception.ServerErrorException;
import org.api.abiblia.exception.UnauthorizedException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Classe responsável por interceptar as Exceções lançadas pelo sistema e
 * redirecionar para as exceções personalizadas.
 * 
 * @author Everson Teixeira <tx.everson@gmail.com>
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link NotFoundException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ErroInfo handleExceptionNotFound(HttpServletResponse response, HttpServletRequest request,
			Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link ServerErrorException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServerErrorException.class)
	public @ResponseBody ErroInfo handleExceptionServerError(HttpServletResponse response, HttpServletRequest request,
			Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link SQLException}, sendo de coluna ou tabela não existente
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public @ResponseBody ErroInfo sqlExceptionError(HttpServletResponse response, HttpServletRequest request,
			InvalidDataAccessResourceUsageException exception) {

		String message = exception.getRootCause().getMessage();
		if (message.contains("Invalid column name") || message.contains("Invalid object name")) {

			ServerErrorException serverErrorException = new ServerErrorException(
					ExceptionsMessagesEnum.GLOBAL_RECURSO_NAO_DISPONIVEL);
			ErroInfo erroInfo = buildErrorInfo(request, serverErrorException);
			return erroInfo;

		} else {

			ErroInfo erroInfo = buildErrorInfo(request, exception);
			return erroInfo;
		}

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link DataAccessResourceFailureException}, sendo quando não consegue
	 * realizar a conexão com a base de dados
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessResourceFailureException.class)
	public @ResponseBody ErroInfo dataAccessSqlExceptionError(HttpServletResponse response, HttpServletRequest request,
			DataAccessResourceFailureException exception) {

		ServerErrorException serverErrorException = new ServerErrorException(
				ExceptionsMessagesEnum.GLOBAL_BASE_NAO_DISPONIVEL);
		ErroInfo erroInfo = buildErrorInfo(request, serverErrorException);
		return erroInfo;
	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link BadRequestException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public @ResponseBody ErroInfo handleExceptionBadRequest(HttpServletResponse response, HttpServletRequest request,
			Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link UnauthorizedException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public @ResponseBody ErroInfo handleExceptionUnauthorized(HttpServletResponse response, HttpServletRequest request,
			Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link ForbiddenException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(ForbiddenException.class)
	public @ResponseBody ErroInfo handleExceptionForbidden(HttpServletResponse response, HttpServletRequest request,
			Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link PreconditionRequiredException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
	@ExceptionHandler(PreconditionRequiredException.class)
	public @ResponseBody ErroInfo handleExceptionPreconditionRequired(HttpServletResponse response,
			HttpServletRequest request, Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por interceptar todas as exceções do tipo
	 * {@link NotImplementedException}
	 * 
	 * @param response  {@link HttpServletResponse}
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	@ExceptionHandler(NotImplementedException.class)
	public @ResponseBody ErroInfo handleExceptionNotImplemented(HttpServletResponse response,
			HttpServletRequest request, Exception exception) {

		ErroInfo erroInfo = buildErrorInfo(request, exception);
		return erroInfo;

	}

	/**
	 * Método responsável por criar o objeto que representa a exceção.
	 * 
	 * @param request   {@link HttpServletRequest}
	 * @param exception {@link Exception}
	 * @return Classe que representa o objeto de retorno {@link ErroInfo}.
	 */
	private ErroInfo buildErrorInfo(HttpServletRequest request, java.lang.Exception exception) {

		Exception exceptionBuild = (Exception) exception;

		ErroInfo erroInfo = new ErroInfo(LocalDateTime.now(), exceptionBuild.getMsgEnum().getHttpCode(),
				exceptionBuild.getClass().getSimpleName(), exceptionBuild.getMessage(), request.getRequestURI());
		return erroInfo;
	}

	/**
	 * 
	 * Classe que representa o objeto de retorno das exceções lançadas pelo PIER.
	 * 
	 * @author Thiago Sampaio <thiago.sampaio@conductor.com.br>
	 *
	 */
	@AllArgsConstructor
	@Getter
	public class ErroInfo {

		/**
		 * Timestamp do momento em que a exceção foi lançada.
		 */
		@DateTimeFormat(iso = ISO.DATE_TIME)
		public LocalDateTime timestamp;

		/**
		 * Código identificador do tipo de exceção;
		 */
		public Integer code;

		/**
		 * Nome da classe de exceção.
		 */
		public String exception;

		/**
		 * Mensagem com a descrição da exceção.
		 */
		public String message;

		/**
		 * Path que realizou a solicitação que causou a exceção.
		 */
		public String path;

	}

}