package com.mcdan.mcdanfood.infrastructure.repository;

import org.springframework.stereotype.Component;


@Component
public class CozinhaRepositoryImpl{

/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> todas() {
		//JPQL
		 TypedQuery<Cozinha> query = manager.createQuery("from Cozinha",Cozinha.class);
		 
		 return query.getResultList();
	}
	
	@Override
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Override
	public Cozinha buscarPorId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Override
	@Transactional
	public void remover(Long id) {	
		
		Cozinha cozinha = buscarPorId(id);
		
		if(cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinha);
	}
*/
}
