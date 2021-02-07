package com.mcdan.mcdanfood.di.notificacao;

import com.mcdan.mcdanfood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}