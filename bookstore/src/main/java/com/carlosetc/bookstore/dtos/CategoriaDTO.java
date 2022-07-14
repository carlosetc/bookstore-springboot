package com.carlosetc.bookstore.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.carlosetc.bookstore.domain.Categoria;

public class CategoriaDTO implements EntidadeDTO<Categoria> {

	private Integer id;

	@NotEmpty(message = "O campo NOME é requerido")
	@Length(min = 2, max = 100, message = "O campo NOME deve ter entre 2 e 100 caracteres")
	private String nome;

	@NotEmpty(message = "O campo DESCRIÇÃO é requerido")
	@Length(min = 10, max = 200, message = "O campo DESCRIÇÃO deve ter entre 10 e 200 caracteres")
	private String descricao;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.descricao = categoria.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Categoria toEntity() {
		return new Categoria(this);
	}

}
