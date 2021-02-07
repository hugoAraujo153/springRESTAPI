package com.mcdan.mcdanfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcdan.mcdanfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long>{
	
	
	//Os repositorios são criados por aggregate root no dominio 
//	List<Cozinha> todas();
//	Cozinha buscarPorId(Long id);
//	Cozinha salvar(Cozinha cozinha);
//	void remover(Long id);
	
	
	List<Cozinha> findTodasByNome(String nome);
	
	Optional<Cozinha> findByNome(String nome);

	boolean existsByNome(String nome);
	
}
