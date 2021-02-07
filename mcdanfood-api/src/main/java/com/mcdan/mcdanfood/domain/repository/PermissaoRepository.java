package com.mcdan.mcdanfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcdan.mcdanfood.domain.model.Cozinha;
import com.mcdan.mcdanfood.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
/*
	List<Permissao> todas();
	Permissao buscarPorId(Long id);
	Permissao adicionar(Permissao permissao);
	void remover(Permissao permissao);
*/	
	
	
	
}