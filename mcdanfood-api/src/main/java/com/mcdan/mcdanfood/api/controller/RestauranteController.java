package com.mcdan.mcdanfood.api.controller;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.mcdan.mcdanfood.domain.CadastroRestauranteService;
import com.mcdan.mcdanfood.domain.exception.CozinhaNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.NegocioException;
import com.mcdan.mcdanfood.domain.model.Restaurante;
import com.mcdan.mcdanfood.domain.repository.RestauranteRepository;

@RestController

//@ResponseBody //A resposta dos metodos vai como resposta do pedido http
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurante buscar(@PathVariable Long restauranteId) {
		return cadastroRestauranteService.buscaOuFalha(restauranteId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody  @Valid Restaurante restaurante) {
		
			 return cadastroRestauranteService.salvar(restaurante);
	}
	
	/*
	@PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
        @RequestBody Restaurante restaurante) {
        try {
        	System.out.println(restaurante.toString());
			Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
			
	
			//copia todas as propriedades
			if (restauranteAtual.isPresent()) { //Parece que o copyProperties nao está a funcionar
				BeanUtils.copyProperties(restaurante, restauranteAtual, 
			"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
				

				System.out.println(restauranteAtual.toString());
				cadastroRestauranteService.salvar(restauranteAtual.get());
				
				return ResponseEntity.ok(restauranteAtual.get());
			}
			
			return ResponseEntity.notFound().build(); 
		
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
    }
	*/
	
	
	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid Restaurante restaurante) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(restauranteId);
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, 
				"id", "formasPagamento", "endereco", "dataCadastro", "produtos");
		try {	
			
			return cadastroRestauranteService.salvar(restauranteAtual);
			
		}catch(CozinhaNaoEncontradaException ex) {
			throw new NegocioException(ex.getMessage());
		}	
	}
	
	@PatchMapping("/{restauranteId}")
	public Restaurante atualizarParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = cadastroRestauranteService.buscaOuFalha(restauranteId);
		
		merge(campos, restauranteAtual);
		
		return atualizar(restauranteId, restauranteAtual);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}
	
	public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId){
		//TODO:Implementar
		return null;
	}
}
