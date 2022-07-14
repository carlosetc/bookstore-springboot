package com.carlosetc.bookstore.services;

public abstract class EntidadeService<ID, T> {

	protected abstract T obterEntidadePorId(ID id);
	
}
