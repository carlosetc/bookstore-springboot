package com.carlosetc.bookstore.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.carlosetc.bookstore.domain.Categoria;
import com.carlosetc.bookstore.dtos.CategoriaDTO;
import com.carlosetc.bookstore.exceptions.ObjetoNaoEncontradoException;
import com.carlosetc.bookstore.exceptions.ViolacaoIntegridadeDadosException;
import com.carlosetc.bookstore.repositories.CategoriaRepository;

@Service
public class CategoriaService extends EntidadeService<Integer, Categoria> {
	
	@Autowired
	private CategoriaRepository repository;
	
	public CategoriaDTO obterPorId(Integer id) {
		return new CategoriaDTO(obterEntidadePorId(id));
	}

	@Override
	protected Categoria obterEntidadePorId(Integer id) {
		Optional<Categoria> opt = this.repository.findById(id);
		return opt.orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada (id="+id+")"));
	}

	public List<CategoriaDTO> obterTodos() {
		return repository.findAll().stream().map(c -> new CategoriaDTO(c)).collect(Collectors.toList());
	}

	public CategoriaDTO criar(CategoriaDTO dto) {
		dto.setId(null);
		return new CategoriaDTO(repository.save(dto.toEntity()));
	}

	public CategoriaDTO atualizar(Integer id, CategoriaDTO categoria) {
		Categoria c = obterEntidadePorId(id);
		c.setDescricao(categoria.getDescricao());
		c.setNome(categoria.getNome());
		return new CategoriaDTO(repository.save(c));
	}

	public void excluir(Integer id) {
		Categoria c = obterEntidadePorId(id);
		try {
			repository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadosException("Categoria não pode ser excluída. Há livros associados.", e);
		}
	}
	
}
