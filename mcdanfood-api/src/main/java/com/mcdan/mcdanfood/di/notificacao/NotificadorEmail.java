package com.mcdan.mcdanfood.di.notificacao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mcdan.mcdanfood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//@Qualifier("normal") //identificador
@Component
public class NotificadorEmail implements Notificador {
	
//	private boolean upperCase;
//	private String hostServidorSmpt;
	
//	public NotificadorEmail(String hostServidorSmtp) {
//		this.hostServidorSmpt = hostServidorSmtp;
//		System.out.println("Construtor NotificadorEmail");
//	}

	public NotificadorEmail() {
		System.out.println("NotificadorEmail REAL");
	} 
	
	@PostConstruct
	public void init() {
		System.out.println("INIT");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	@Override	
	public void notificar(Cliente cliente,String mensagem) {
//		if(this.upperCase) 
//			mensagem = mensagem.toUpperCase();
		
		
		System.out.printf("Notificando %s atrav�s do email %s : %s \n",
						cliente.getNome(),cliente.getEmail(),/*this.hostServidorSmpt,*/mensagem);
	}

//	public boolean isUpperCase() {
//		return upperCase;
//	}
//
//	public void setUpperCase(boolean upperCase) {
//		this.upperCase = upperCase;
//	}
}
