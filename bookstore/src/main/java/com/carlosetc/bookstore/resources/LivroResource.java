package com.carlosetc.bookstore.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carlosetc.bookstore.dtos.LivroDTO;
import com.carlosetc.bookstore.services.LivroService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> findById(@PathVariable Integer id) {
		LivroDTO livro = service.obterPorId(id);
		return ResponseEntity.ok().body(livro);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "id_categoria", required = false) Integer idCategoria) {
		List<LivroDTO> livros = service.obterTodos(idCategoria);
		return ResponseEntity.ok().body(livros);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@PathVariable Integer id, @Valid @RequestBody LivroDTO livro) {
		LivroDTO atualizado = service.atualizar(id, livro);
		return ResponseEntity.ok().body(atualizado);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<LivroDTO> updatePatch(@PathVariable Integer id, @Valid @RequestBody LivroDTO livro) {
		LivroDTO atualizado = service.atualizar(id, livro);
		return ResponseEntity.ok().body(atualizado);
	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> create(@RequestParam(value = "id_categoria") Integer idCategoria, @Valid @RequestBody LivroDTO livro) {
		LivroDTO novo = service.criar(idCategoria, livro);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(novo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
}
