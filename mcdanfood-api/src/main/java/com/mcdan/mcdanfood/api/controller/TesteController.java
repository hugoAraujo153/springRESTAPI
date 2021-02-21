package com.mcdan.mcdanfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.model.Restaurante;
import com.mcdan.mcdanfood.domain.repository.CozinhaRepository;
import com.mcdan.mcdanfood.domain.repository.RestauranteRepository;
import com.mcdan.mcdanfood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.mcdan.mcdanfood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List <Cozinha> cozinhasPorNome(String nome){
		return cozinhaRepository.findTodasByNome(nome);
	}
	
	
	@GetMapping("/cozinhas/unica-por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome){
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists(String nome){
		return cozinhaRepository.existsByNome(nome);
	}
	
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial,BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	
	@GetMapping("/restaurantes/consultar-por-nome")
	public List<Restaurante> consultarPorNomeECozinhaId(String nome,Long cozinhaId){
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}

	
	
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorNomeECozinhaId(String nome,Long cozinhaId){
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}
	
	@GetMapping("/restaurantes/top2-por-nome")
	public List<Restaurante> restaurantesTop2PorNome(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome){
		Specification<Restaurante> comFreteGratis = new RestauranteComFreteGratisSpec();

		Specification<Restaurante> comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
	}
}
