package com.carlosetc.bookstore.exceptions;

public class ViolacaoIntegridadeDadosException extends RuntimeException {

	private static final long serialVersionUID = 4453999405919610422L;

	public ViolacaoIntegridadeDadosException(String message, Throwable cause) {
		super(message, cause);
	}

	public ViolacaoIntegridadeDadosException(String message) {
		super(message);
	}

}
