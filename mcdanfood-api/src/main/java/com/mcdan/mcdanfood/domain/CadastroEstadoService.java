package com.mcdan.mcdanfood.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.model.Estado;
import com.mcdan.mcdanfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void remover(long id) {
		try {
			 estadoRepository.deleteById(id);
		}catch(EmptyResultDataAccessException ex) {
			throw new EntidadeNaoEncontradaException(String.format(" Não existe cadastro de estado com código %d", id));
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format("A estado com o id %d encontra-se em uso", id));
		
		}
		
	}

}
