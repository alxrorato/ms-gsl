package com.dev.gslentrega.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dev.gslentrega.entities.EnderecoDestino;
import com.dev.gslentrega.entities.EnderecoOrigem;

public class MockUtils {

	public static final BigDecimal DISTANCIA_LIMITE = new BigDecimal(1500.0);
	public static final int SCALE_2 = 2;
	public static final int SCALE_4 = 4;
	
	private static final List<String> RECEBEDORES_MERCADORIAS = Arrays.asList("Beatriz Cavalcanti Souza", "Carla Martins Souza", "portaria do condomínio",
			"Carlos Castro Goncalves", "Sofia Araujo Lima", "Luan Silva Pereira", "Bruna Lima Azevedo", "Ryan Dias Costa",
			"Enzo Rocha Oliveira", "Brenda Castro Alves", "Luana Pereira Azevedo", "André Martins Barbosa",
			"Matilde Pereira Melo", "Vitoria Rocha Barros", "Júlio Goncalves Barbosa", "Pedro Ribeiro Carvalho",
			"Felipe Carvalho Goncalves", "Caio Dias Sousa", "João Gomes Oliveira", "Vitória Silva Rocha", "Mateus Melo Cunha",
			"Lucas Silva Lima", "Vitória Melo Dias", "Gabrielle Ferreira Rodrigues", "Isabela Correia Castro", "Mateus Martins Araujo", 
			"Tiago Alves Carvalho", "Anna Barros Fernandes", "Sophia Gomes Oliveira", "Camila Rodrigues Ribeiro");
	
	private static final List<String> MOTIVOS_CANCELAMENTOS_ENTREGAS = Arrays.asList("Desistência por parte do cliente", "Imprevisto durante o transporte", 
			"Entrega iria demorar além do prazo previsto");
	/*
	public static final Map<Double, Double> TABELA_PRECO_PRETE_POR_KG = Stream.of(new Object[][] { 
	     { 0.0, 1.0 }, 
	     { 250.0, 1.5 }, 
	     { 500.0, 3.0 },
	     { 750.0, 4.5 },
	 }).collect(Collectors.toMap(data -> (Double) data[0], data -> (Double) data[1]));
	*/
	public static final Map<BigDecimal, BigDecimal> TABELA_PRECO_PRETE_POR_KG = Stream.of(new Object[][] { 
	     { new BigDecimal(0.0), new BigDecimal(1.0) }, 
	     { new BigDecimal(250.0), new BigDecimal(1.5) }, 
	     { new BigDecimal(500.0), new BigDecimal(3.0) },
	     { new BigDecimal(750.0), new BigDecimal(4.5) },
	 }).collect(Collectors.toMap(data -> (BigDecimal) data[0], data -> (BigDecimal) data[1]));
	
	//Mocks Google Maps

	public static int getDistancia(int limit) {
		int distancia = 0;
		do {
			distancia = RandomUtils.getRandomNumber(limit);
		} while (distancia == 0);
		return distancia;
	}
	
	public static BigDecimal getDistancia(BigDecimal limit) {
		BigDecimal distancia = GeneralUtils.ZERO;
		do {
			distancia = RandomUtils.getRandomNumber(GeneralUtils.ZERO, limit, SCALE_2);
		} while (distancia == GeneralUtils.ZERO);
		return distancia;
	}
	
	public static BigDecimal getLatitudeLongitude(BigDecimal limit) {
		BigDecimal latitudeLongitude = GeneralUtils.ZERO;
		do {
			latitudeLongitude = RandomUtils.getRandomNumber(GeneralUtils.UM, limit, SCALE_4);
		} while (latitudeLongitude == GeneralUtils.ZERO);
		return latitudeLongitude;
	}

	private static long getDaysPrevisaoEntrega(EnderecoOrigem enderecoOrigem, EnderecoDestino enderecoDestino, BigDecimal distancia) {
		if (enderecoOrigem.getUf().equals(enderecoDestino.getUf()) 
				&& enderecoOrigem.getCidade().equals(enderecoDestino.getCidade())) {
			
				return 0;
		} 
		
		if (distancia.compareTo(GeneralUtils.DUZENTOS) < 0) {
			return 1;
		} else if (distancia.compareTo(GeneralUtils.DUZENTOS) >= 0 && distancia.compareTo(GeneralUtils.QUATROCENTOS) < 0) {
			return 2;
		} else if (distancia.compareTo(GeneralUtils.QUATROCENTOS) >= 0 && distancia.compareTo(GeneralUtils.SEISCENTOS) < 0) {
			return 3;
		} else if (distancia.compareTo(GeneralUtils.SEISCENTOS) >= 0 && distancia.compareTo(GeneralUtils.OITOCENTOS) < 0) {
			return 4;
		} else {
			return 5;
		}
	}
	
	public static LocalDate getDataPrevisaoEntrega(LocalDateTime dataSolicitacao, EnderecoOrigem enderecoOrigem, 
			EnderecoDestino enderecoDestino, BigDecimal distancia) {

		return dataSolicitacao.plusDays(getDaysPrevisaoEntrega(enderecoOrigem, 
				enderecoDestino, distancia)).toLocalDate();
	}
/*	
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
*/
	public static BigDecimal getPrecoPorKg(BigDecimal pesoCarga) {
		Map<BigDecimal, BigDecimal> mapFretePorkgOrdenado = new TreeMap<BigDecimal, BigDecimal>();
		mapFretePorkgOrdenado.putAll(TABELA_PRECO_PRETE_POR_KG);		
		BigDecimal valuePreco = new BigDecimal(1.0);
		Set<BigDecimal> keys = mapFretePorkgOrdenado.keySet();
		Iterator<BigDecimal> i = keys.iterator();
		while (i.hasNext()) {
			BigDecimal keyPeso = (BigDecimal)i.next();
			valuePreco = mapFretePorkgOrdenado.get(keyPeso);
			System.out.println("Key: " + keyPeso + " - Value: " + valuePreco);
			if (pesoCarga.compareTo(keyPeso) < 0) {
				break;
			}
		}
		return valuePreco;
	}

	public static String getRecebedorMercadoria() {
		
		return RECEBEDORES_MERCADORIAS.get(RandomUtils.getRandomNumber(RECEBEDORES_MERCADORIAS.size() - 1));
		
	}
	
	public static String getMotivoCancelamentoEntrega() {
		
		return MOTIVOS_CANCELAMENTOS_ENTREGAS.get(RandomUtils.getRandomNumber(MOTIVOS_CANCELAMENTOS_ENTREGAS.size() - 1));
		
	}
}
