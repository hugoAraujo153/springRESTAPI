package com.mcdan.mcdanfood.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.CozinhaNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "A cozinha com o id %d encontra-se em uso";
	
	@Autowired
	CozinhaRepository cozinhaRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		//Regras de negócio
		return cozinhaRepository.save(cozinha);
	}
	
	
	public void excluir(Long id) {
		try {
			
			cozinhaRepository.deleteById(id);
			
		}catch(EmptyResultDataAccessException ex) {
			throw new CozinhaNaoEncontradaException(id);
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
		
		}
	}

	public Cozinha buscaOuFalha(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId)
				.orElseThrow(()->
					new CozinhaNaoEncontradaException(cozinhaId));
	}
}
