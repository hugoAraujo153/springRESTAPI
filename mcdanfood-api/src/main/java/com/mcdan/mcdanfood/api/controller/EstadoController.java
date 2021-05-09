package com.mcdan.mcdanfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mcdan.mcdanfood.domain.CadastroEstadoService;
import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.model.Estado;
import com.mcdan.mcdanfood.domain.repository.EstadoRepository;

@RestController
//@ResponseBody //A resposta dos metodos vai como resposta do pedido http
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.OK)
	public Estado buscar(@PathVariable("estadoId")  long estadoId){
		return cadastroEstadoService.buscaOuFalha(estadoId);	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)  
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado); 
	}
	
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable("estadoId") long id, @RequestBody Estado estado) {
		
		Estado estadoNovo = cadastroEstadoService.buscaOuFalha(id);	
	
		//copia as propriedades que vêm no pedido para o objecto
		BeanUtils.copyProperties(estado, estadoNovo,"id");
		
		return cadastroEstadoService.salvar(estadoNovo);

	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable("estadoId") long id){
		cadastroEstadoService.remover(id);
	}
	
	
}