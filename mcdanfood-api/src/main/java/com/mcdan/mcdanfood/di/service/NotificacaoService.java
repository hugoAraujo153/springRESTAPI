package com.mcdan.mcdanfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mcdan.mcdanfood.di.notificacao.NivelUrgencia;
import com.mcdan.mcdanfood.di.notificacao.Notificador;
import com.mcdan.mcdanfood.di.notificacao.TipoDoNotificador;

@Component
public class NotificacaoService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event){
		notificador.notificar(event.getCliente(), "Evento: Seu registo no sistema está activo");
	}
}
