package com.mcdan.mcdanfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcdan.mcdanfood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
	//Os repositorios são criados por aggregate root no dominio 
/*	List<FormaPagamento> todas();
	FormaPagamento buscarPorId(Long id);
	FormaPagamento adicionar(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
	*/
}
