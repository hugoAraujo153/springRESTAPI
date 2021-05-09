package com.mcdan.mcdanfood.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.CidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.model.Cidade;
import com.mcdan.mcdanfood.domain.model.Estado;
import com.mcdan.mcdanfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_ENTIDADE_EM_USO = "A cidade com o id %d encontra-se em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService estadoService;

	public Cidade salvar(Cidade cidade) {

		Estado estado = estadoService.buscaOuFalha(cidade.getEstado().getId());
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}

	public void remover(long id) {
		try {
			
			 cidadeRepository.deleteById(id);
			 
		}catch(EmptyResultDataAccessException ex) {
			throw new CidadeNaoEncontradaException(id);
		}catch (DataIntegrityViolationException ex) {
			
			 throw new EntidadeEmUsoException(String.format(MSG_ENTIDADE_EM_USO, id));
		
		}
		
	}
	
	public Cidade buscaOuFalha(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				()->new CidadeNaoEncontradaException(cidadeId));
	}

}
