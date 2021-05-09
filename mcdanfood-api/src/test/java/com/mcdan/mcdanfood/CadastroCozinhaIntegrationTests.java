package com.mcdan.mcdanfood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mcdan.mcdanfood.domain.CadastroCozinhaService;
import com.mcdan.mcdanfood.domain.model.Cozinha;


@RunWith(SpringRunner.class)
@SpringBootTest
class CadastroCozinhaIntegrationTests {


	@Autowired
	CadastroCozinhaService cadastroCozinhaService;
	
	@Test 
	public void testarCadastroCozinhaComSucesso() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		//acção
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
		
		//validação
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
		
	}

}
