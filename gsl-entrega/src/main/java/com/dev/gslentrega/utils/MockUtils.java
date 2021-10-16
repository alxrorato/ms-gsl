package com.dev.gslentrega.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;

public class MockUtils {

	private static final int DISTANCIA_LIMITE = 1500;

	public static final Map<Double, Double> TABELA_PRECO_PRETE_POR_KG = Stream.of(new Object[][] { 
	     { 0.0, 1.0 }, 
	     { 250.0, 1.5 }, 
	     { 500.0, 3.0 },
	     { 750.0, 4.5 },
	 }).collect(Collectors.toMap(data -> (Double) data[0], data -> (Double) data[1]));
	
	
	//Mocks Google Maps

	public static int getDistancia() {
		return RandomUtils.getRandomNumber(DISTANCIA_LIMITE);
	}
	
	private static long getDaysPrevisaoEntrega(EnderecoOrigem enderecoOrigem, EnderecoDestino enderecoDestino, int distancia) {
		if (enderecoOrigem.getUf().equals(enderecoDestino.getUf()) 
				&& enderecoOrigem.getCidade().equals(enderecoDestino.getCidade())) {
			
				return 0;
		} 
		
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
	}
	
	public static LocalDate getDataPrevisaoEntrega(LocalDateTime dataSolicitacao, EnderecoOrigem enderecoOrigem, 
			EnderecoDestino enderecoDestino, int distancia) {

		return dataSolicitacao.plusDays(getDaysPrevisaoEntrega(enderecoOrigem, 
				enderecoDestino, distancia)).toLocalDate();
	}
	
	public static Double getPrecoPorKg(Double pesoCarga) {
		Map<Double, Double> mapFretePorkgOrdenado = new TreeMap<Double, Double>();
		mapFretePorkgOrdenado.putAll(TABELA_PRECO_PRETE_POR_KG);		
		Double valuePreco = 1.0;
		Set<Double> keys = mapFretePorkgOrdenado.keySet();
		Iterator<Double> i = keys.iterator();
		while (i.hasNext()) {
			Double keyPeso = (Double)i.next();
			valuePreco = mapFretePorkgOrdenado.get(keyPeso);
			System.out.println("Key: " + keyPeso + " - Value: " + valuePreco);
			if (pesoCarga < keyPeso) {
				break;
			}
		}
		return valuePreco;
	}
}
