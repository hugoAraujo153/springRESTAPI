package com.mcdan.mcdanfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.mcdan.mcdanfood.di.modelo.Cliente;
import com.mcdan.mcdanfood.di.notificacao.NivelUrgencia;
import com.mcdan.mcdanfood.di.notificacao.Notificador;
import com.mcdan.mcdanfood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	//@Qualifier("urgente")
	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;//Pontos de injecção: Construtor, Setter,no proprio Atributo. o ideal é o ponto de injeçao ser no construtor
	
	@Autowired
	private ApplicationEventPublisher publisher;   
	
	//Quem decide a implementa�ao do notificador � algo de fora, um parametro, invertemos o controle da implementa��o
	//J� n�o � desta classe. IoC - inversion of control
	//@Autowired Autowired indica que é este o construtor chamado pelo spring container
	//@Autowired(required = false)//Injecção opcional da classe
//	public AtivacaoClienteService(Notificador notificadorParametro) {
//		this.notificador = notificadorParametro;
//		System.out.println("AtivacaoClienteService Construtor "+notificadorParametro);
//	}
//	
	
//	public AtivacaoClienteService(String qualquer) {
//		
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		publisher.publishEvent(new ClienteAtivadoEvent(cliente));
	
		if(notificador != null) {//nao é obrigatoria se o autowired estiver com required a false
			this.notificador.notificar(cliente, "O seu registo no sistema est� activo");
		}else {
			System.out.println("Nao existe notificador mas o cliente foi ativado");
		}
	}


	//@Autowired
	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}
	
	
	
}
