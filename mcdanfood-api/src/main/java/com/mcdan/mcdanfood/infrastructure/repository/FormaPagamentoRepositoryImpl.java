package com.mcdan.mcdanfood.infrastructure.repository;

import org.springframework.stereotype.Component;

@Component
public class FormaPagamentoRepositoryImpl /* implements FormaPagamentoRepository*/ {
/*	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> todas() {
		//JPQL
		 TypedQuery<FormaPagamento> query = manager.createQuery("from FormaPagamento",FormaPagamento.class);
		 
		 return query.getResultList();
	}
	
	@Override
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Override
	public FormaPagamento buscarPorId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Override
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = buscarPorId(formaPagamento.getId());
		
		manager.remove(formaPagamento);
	}
	*/
}
