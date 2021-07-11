package com.mcdan.mcdanfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.CadastroCozinhaService;
import com.mcdan.mcdanfood.domain.exception.CozinhaNaoEncontradaException;
import com.mcdan.mcdanfood.domain.exception.EntidadeEmUsoException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		novaCozinha.setId(1L);
		
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		assertThrows(ConstraintViolationException.class, ()-> cadastroCozinha.salvar(novaCozinha));
	}
	
    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
    	assertThrows(EntidadeEmUsoException.class, ()->  cadastroCozinha.excluir(1L));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
    	assertThrows(CozinhaNaoEncontradaException.class, ()->  cadastroCozinha.excluir(100L));
    }       
}
