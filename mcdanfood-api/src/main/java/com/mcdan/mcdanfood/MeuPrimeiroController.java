package com.mcdan.mcdanfood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mcdan.mcdanfood.di.modelo.Cliente;
import com.mcdan.mcdanfood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {

	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		System.out.println("MeuPrimeiroController Construtor " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente joao = new Cliente("Joao","joao@xyz","123123123");
		Cliente maria = new Cliente("Maria","maria@xyz","321321321");

		ativacaoClienteService.ativar(joao);
		ativacaoClienteService.ativar(maria);
		
		return "hello";
	}
}
