package com.mcdan.mcdanfood.domain;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcdan.mcdanfood.domain.exception.RestauranteNaoEncontradoException;
import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.model.Restaurante;
import com.mcdan.mcdanfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private CadastroCozinhaService cozinhaService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	

	
	public Restaurante salvar(Restaurante restaurante) {
		
		Cozinha cozinhaAlterada = restaurante.getCozinha();
		
		if(cozinhaAlterada != null) {

			Cozinha cozinha = cozinhaService.buscaOuFalha(cozinhaAlterada.getId());
			BeanUtils.copyProperties(cozinhaAlterada, cozinha);
			
			restaurante.setCozinha(cozinha);
		}
		
		return restauranteRepository.save(restaurante);
		
	}
	
	public Restaurante buscaOuFalha(Long restauranteId) {
		return restauranteRepository.findById(restauranteId)
			.orElseThrow(() -> new RestauranteNaoEncontradoException( restauranteId));
	}
	
	//TODO:Implementar
	public void excluir(Long restauranteId) {
		
	}

}
