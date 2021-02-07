package com.mcdan.mcdanfood.di.notificacao;

import org.springframework.stereotype.Component;

import com.mcdan.mcdanfood.di.modelo.Cliente;

//@Primary//como há dois beans notificador dizemos que este é primary na altura da desambiguaçao
//@Qualifier("urgente")
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s atrav�s do SMS usando o telefone %s: %s \n",
				cliente.getNome(),cliente.getTelefone(),mensagem);
		
	}
}
