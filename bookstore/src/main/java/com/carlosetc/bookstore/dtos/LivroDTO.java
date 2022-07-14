package com.carlosetc.bookstore.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.carlosetc.bookstore.domain.Livro;

public class LivroDTO implements EntidadeDTO<Livro> {

	private Integer id;
	
	@NotEmpty(message = "O campo TÍTULO é requerido")
	@Length(min = 3, max = 50, message = "O campo TÍTULO deve ter entre 3 e 50 caracteres")
	private String titulo;
	
	@NotEmpty(message = "O campo NOME DO AUTOR é requerido")
	@Length(min = 3, max = 50, message = "O campo NOME DO AUTOR deve ter entre 3 e 50 caracteres")
	private String nomeAutor;
	
	@NotEmpty(message = "O campo TEXTO é requerido")
	@Length(min = 10, max = 2000000, message = "O campo TEXTO deve ter entre 10 e 2000000 caracteres")
	private String texto;
	
	private CategoriaDTO categoria;

	public LivroDTO() {
	}

	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.nomeAutor = livro.getNomeAutor();
		this.texto = livro.getTexto();
		this.categoria = new CategoriaDTO(livro.getCategoria());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	@Override
	public Livro toEntity() {
		return new Livro(this);
	}

}
