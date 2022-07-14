package com.carlosetc.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosetc.bookstore.domain.Categoria;
import com.carlosetc.bookstore.domain.Livro;
import com.carlosetc.bookstore.repositories.CategoriaRepository;
import com.carlosetc.bookstore.repositories.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void instanciarDatabase() {
		this.livroRepository.deleteAll();
		this.categoriaRepository.deleteAll();
		
		Categoria c1 = new Categoria(null, "Informática", "Tecnologia da Informação");
		Categoria c2 = new Categoria(null, "Poesia", "Poesia Nacional e Estrangeira");
		Categoria c3 = new Categoria(null, "Economia", "Ciências Econômicas");
		
		Livro l1 = new Livro(null, "Spring Boot em baianês", "Bruno Grilo", "É... é... é...", c1);
		Livro l2 = new Livro(null, "C++", "Lalinho", "i+++++++++++", c1);
		
		Livro l3 = new Livro(null, "Cristal", "Paul Celan", "nonono nono", c2);
		Livro l4 = new Livro(null, "Notícias da Ilha", "Ledusha Spinardi", "nanana an anana anana", c2);
		Livro l5 = new Livro(null, "Todos os Poemas", "Ferreira Gullar", "ni ni ninini nini ni", c2);
		
		c1.getLivros().addAll(Arrays.asList(l1, l2));
		c2.getLivros().addAll(Arrays.asList(l3, l4, l5));
		
		this.categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
	
}
