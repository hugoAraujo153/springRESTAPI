package com.mcdan.mcdanfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.mcdan.mcdanfood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

}
