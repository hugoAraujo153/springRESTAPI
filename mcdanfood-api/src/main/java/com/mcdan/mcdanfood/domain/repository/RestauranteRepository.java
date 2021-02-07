package com.mcdan.mcdanfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcdan.mcdanfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	/*
	//Os repositorios são criados por aggregate root no dominio 
	List<Restaurante> todas();
	Restaurante buscarPorId(Long id);
	Restaurante salvar(Restaurante Restaurante);
	void remover(Restaurante Restaurante);
*/
		@Query("from Restaurante where nome like %:nome% and cozinha.id=:id")
		List<Restaurante> restaurantesPorNome(String nome,@Param("id")Long cozinhaId);
		
		 List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial,BigDecimal taxaFinal);
		 
		 List<Restaurante> findByNomeContainingAndCozinhaId(String nome,Long id);
		 
		 List<Restaurante> findTop2ByNomeContaining(String nome);
}
