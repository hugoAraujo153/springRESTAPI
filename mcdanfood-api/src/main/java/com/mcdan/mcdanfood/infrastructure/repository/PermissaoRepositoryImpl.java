package com.mcdan.mcdanfood.infrastructure.repository;

import org.springframework.stereotype.Component;

@Component
public class PermissaoRepositoryImpl /*implements PermissaoRepository */{
/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> todas() {
		//JPQL
		 TypedQuery<Permissao> query = manager.createQuery("from Permissao",Permissao.class);
		 
		 return query.getResultList();
	}
	
	@Override
	@Transactional
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Override
	public Permissao buscarPorId(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Override
	@Transactional
	public void remover(Permissao permissao) {
		permissao = buscarPorId(permissao.getId());
		
		manager.remove(permissao);
	}
	*/
}
