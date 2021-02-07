package com.mcdan.mcdanfood.di.service;

import org.springframework.context.ApplicationEvent;

import com.mcdan.mcdanfood.di.modelo.Cliente;

public class ClienteAtivadoEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7297980210739338034L;
	
	private Cliente cliente;

	public ClienteAtivadoEvent(Cliente source) {
		super(source);
		this.cliente= source;
	}

	public Cliente getCliente() {
		return cliente;
	}

}
