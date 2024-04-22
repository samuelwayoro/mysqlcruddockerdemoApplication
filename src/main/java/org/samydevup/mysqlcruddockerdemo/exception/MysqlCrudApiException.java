package org.samydevup.mysqlcruddockerdemo.exception;

import org.springframework.http.HttpStatus;

/**
 * UTILISER POUR VALIDER LES PARAMETRES DES REQUETTES ENVOYEES PAR LE UI
 * 
 * @author samysamwell
 *
 */
public class MysqlCrudApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MysqlCrudApiException(HttpStatus status, String message, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public MysqlCrudApiException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

}
