package com.dev.gslentrega.utils;

import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;

public class MockUtils {

	private static final int DISTANCIA_LIMITE = 1500;
	
	//Mocks Google Maps
	public int getDaysPrevisaoEntrega(EnderecoOrigem enderecoOrigem, EnderecoDestino enderecoDestino, int distancia) {
		if (enderecoOrigem.getUf().equals(enderecoDestino.getUf()) 
				&& enderecoOrigem.getCidade().equals(enderecoDestino.getCidade())) {
			
				return 0;
		} 
		
		return 1;
		/*
		if (distancia < 200) {
			return 1;
		} else if (distancia >= 200 && distancia < 400) {
			return 2;
		} else if (distancia >= 400 && distancia < 600) {
			return 3;
		} else if (distancia >= 600 && distancia < 800) {
			return 4;
		} else {
			return 5;
		}
		*/
	}
	
	public int getDistancia() {
		return RandomUtils.getRandomNumber(DISTANCIA_LIMITE);
	}
}
