package com.mcdan.mcdanfood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mcdan.mcdanfood.api.exceptionHandler.Problem;
import com.mcdan.mcdanfood.domain.CadastroCidadeService;
import com.mcdan.mcdanfood.domain.exception.EntidadeNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EstadoNaoEncontradoException;
import com.mcdan.mcdanfood.domain.exception.NegocioException;
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
	@ResponseStatus(HttpStatus.OK)
	public Cidade buscar(@PathVariable("cidadeId")  long cidadeId){
		
		return cadastroCidadeService.buscaOuFalha(cidadeId);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
			return cadastroCidadeService.salvar(cidade);
		}catch(EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(),ex);
		}	
	}
	
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable("cidadeId") long id, @RequestBody Cidade cidade) {
		
		Cidade cidadeNova = cadastroCidadeService.buscaOuFalha(id);
	
		//copia as propriedades que vêm no pedido para o objecto
		BeanUtils.copyProperties(cidade, cidadeNova,"id");
		
		try {
			return cadastroCidadeService.salvar(cidadeNova);
		}catch(EstadoNaoEncontradoException ex) {
			throw new NegocioException(ex.getMessage(), ex);
		}	 
		
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover (@PathVariable("cidadeId") long id){
		cadastroCidadeService.remover(id);
	}
	

	
}