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
import org.springframework.web.bind.annotation.RestController;

import com.mcdan.mcdanfood.domain.CadastroCidadeService;
import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.model.Cidade;
import com.mcdan.mcdanfood.domain.repository.CidadeRepository;

@RestController
//@ResponseBody //A resposta dos metodos vai como resposta do pedido http
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId")  long cidadeId){
		
		Optional <Cidade> cidade = cidadeRepository.findById(cidadeId);
		
		if(cidade.isPresent()) {
			return ResponseEntity.ok(cidade.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cidade = cadastroCidadeService.salvar(cidade);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@PathVariable("cidadeId") long id, @RequestBody Cidade cidade) {
		
		Optional<Cidade> cidadeNova = cidadeRepository.findById(id);
		
		if(cidadeNova.isPresent()) {
			//copia as propriedades que vêm no pedido para o objecto
			BeanUtils.copyProperties(cidade, cidadeNova,"id");
			
			cadastroCidadeService.salvar(cidadeNova.get());
			
			return ResponseEntity.ok(cidade);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover (@PathVariable("cidadeId") long id){

		try {
			cadastroCidadeService.remover(id);
			return ResponseEntity.noContent().build();
		
		}catch (EntidadeNaoEncontradaException ex) {
			return ResponseEntity.notFound().build();
		}catch (EntidadeEmUsoException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	
}