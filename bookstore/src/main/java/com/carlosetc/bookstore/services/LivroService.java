package com.carlosetc.bookstore.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carlosetc.bookstore.domain.Livro;
import com.carlosetc.bookstore.dtos.CategoriaDTO;
import com.carlosetc.bookstore.dtos.LivroDTO;
import com.carlosetc.bookstore.exceptions.ObjetoNaoEncontradoException;
import com.carlosetc.bookstore.repositories.LivroRepository;

@Service
public class LivroService extends EntidadeService<Integer, Livro> {

	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public LivroDTO obterPorId(Integer id) {
		return new LivroDTO(obterEntidadePorId(id));
	}

	@Override
	protected Livro obterEntidadePorId(Integer id) {
		Optional<Livro> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ObjetoNaoEncontradoException("Livro não encontrada (id="+id+")"));
	}

	public List<LivroDTO> obterTodos(Integer idCategoria) {
		if(idCategoria!=null) {
			categoriaService.obterPorId(idCategoria);
			return repository.findByCategoria(idCategoria).stream().map(l -> new LivroDTO(l)).collect(Collectors.toList());
		} else {
			return repository.findAll().stream().map(l -> new LivroDTO(l)).collect(Collectors.toList());
		}
	}

	public LivroDTO atualizar(Integer id, LivroDTO dto) {
		Livro livro = obterEntidadePorId(id);
		atualizarCampos(livro, dto);
		return new LivroDTO(repository.save(livro));
	}

	private void atualizarCampos(Livro livro, LivroDTO dto) {
		livro.setNomeAutor(dto.getNomeAutor());
		livro.setTexto(dto.getTexto());
		livro.setTitulo(dto.getTitulo());
	}

	public LivroDTO criar(Integer idCategoria, LivroDTO livro) {
		livro.setId(null);
		CategoriaDTO categoria = categoriaService.obterPorId(idCategoria);
		livro.setCategoria(categoria);
		return new LivroDTO(repository.save(livro.toEntity()));
	}

	public void excluir(Integer id) {
		Livro livro = obterEntidadePorId(id);
		repository.delete(livro);
	}

}
