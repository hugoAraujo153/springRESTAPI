package com.mcdan.mcdanfood.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.model.Cidade;
import com.mcdan.mcdanfood.domain.model.Estado;
import com.mcdan.mcdanfood.domain.repository.CidadeRepository;
import com.mcdan.mcdanfood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if (estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de estado com código %d", estadoId));
		}
		
		cidade.setEstado(estado.get());
		
		return cidadeRepository.save(cidade);
	}

	public void remover(long id) {
		try {
			 cidadeRepository.deleteById(id);
			 
		}catch(EmptyResultDataAccessException ex) {
			throw new EntidadeNaoEncontradaException(String.format(" Não existe cadastro de cidade com código %d", id));
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format("A cidade com o id %d encontra-se em uso", id));
		
		}
		
	}
}
