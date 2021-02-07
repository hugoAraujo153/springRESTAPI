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
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId")  long estadoId){
		
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)  
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.salvar(estado); 
	}
	
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") long id, @RequestBody Estado estado) {
		
		Optional<Estado> estadoNovo = estadoRepository.findById(id);
		
		if(estadoNovo.isPresent()) {
			//copia as propriedades que vêm no pedido para o objecto
			BeanUtils.copyProperties(estado, estadoNovo,"id");
			
			cadastroEstadoService.salvar(estadoNovo.get());
			
			return ResponseEntity.ok(estado);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover (@PathVariable("estadoId") long id){

		try {
			cadastroEstadoService.remover(id);
			return ResponseEntity.noContent().build();
		
		}catch (EntidadeNaoEncontradaException ex) {
			return ResponseEntity.notFound().build();
		}catch (EntidadeEmUsoException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
}