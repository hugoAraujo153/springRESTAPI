package com.mcdan.mcdanfood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mcdan.mcdanfood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//@Qualifier("normal") //identificador
@Component
public class NotificadorEmailMock implements Notificador {

	public NotificadorEmailMock(){
		System.out.println("NotificadorEmail MOCK");
	}
	
	@Override	
	public void notificar(Cliente cliente,String mensagem) {
		System.out.printf("MOCK: Notificação seria enviada para %s atrav�s do email %s: %s \n",
						cliente.getNome(),cliente.getEmail(),mensagem);
	}
}
