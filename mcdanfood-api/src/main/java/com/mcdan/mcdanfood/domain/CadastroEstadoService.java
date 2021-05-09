package com.mcdan.mcdanfood.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EstadoNaoEncontradoException;
import com.mcdan.mcdanfood.domain.model.Estado;
import com.mcdan.mcdanfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ENTIDADE_EM_USO = "A estado com o id %d encontra-se em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void remover(long id) {
		try {
			 estadoRepository.deleteById(id);
		}catch(EmptyResultDataAccessException ex) {
			throw new EstadoNaoEncontradoException(id);
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, id));
		
		}
		
	}
	
	public Estado buscaOuFalha(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(
				()->new EstadoNaoEncontradoException(estadoId));
	}

}
