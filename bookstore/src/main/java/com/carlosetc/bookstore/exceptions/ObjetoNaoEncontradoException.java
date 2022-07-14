package com.carlosetc.bookstore.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -3676110567046930566L;

	public ObjetoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjetoNaoEncontradoException(String message) {
		super(message);
	}

}
