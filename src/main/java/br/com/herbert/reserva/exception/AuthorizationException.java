package br.com.herbert.reserva.exception;

import org.springframework.security.access.AccessDeniedException;

public class AuthorizationException extends AccessDeniedException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String msg) {
		super(msg);
	}

	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
