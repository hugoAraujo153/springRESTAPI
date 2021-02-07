package com.mcdan.mcdanfood.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

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
			throw new EntidadeNaoEncontradaException(String.format(" Não existe cadastro de cozinha com código %d", id));
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format("A cozinha com o id %d encontra-se em uso", id));
		
		}
	}

}
