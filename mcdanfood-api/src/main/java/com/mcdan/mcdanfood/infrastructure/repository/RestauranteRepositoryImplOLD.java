package com.mcdan.mcdanfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mcdan.mcdanfood.domain.model.Restaurante;
import com.mcdan.mcdanfood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImplOLD{
/*
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> todas() {
		//JPQL
		 TypedQuery<Restaurante> query = manager.createQuery("from Restaurante",Restaurante.class);
		 
		 return query.getResultList();
	}
	
	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Override
	public Restaurante buscarPorId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = buscarPorId(restaurante.getId());
		
		manager.remove(restaurante);
	}
	
	*/
}
