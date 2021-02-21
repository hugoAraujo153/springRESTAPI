package com.mcdan.mcdanfood.infrastructure.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mcdan.mcdanfood.domain.model.Restaurante;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {
	
	private String nome;

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return criteriaBuilder.like(root.get("nome"),"%"+nome+"%");
	}

}
