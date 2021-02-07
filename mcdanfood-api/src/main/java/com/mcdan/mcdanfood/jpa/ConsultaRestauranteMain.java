package com.mcdan.mcdanfood.jpa;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.mcdan.mcdanfood.McdanfoodApiApplication;
import com.mcdan.mcdanfood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args){
		
		ApplicationContext applicationContext =  (ApplicationContext) new SpringApplicationBuilder(McdanfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
	
	//RestauranteRepository cozinhaRepository = applicationContext.getBean(RestauranteRepository.class);
	}
	
}
