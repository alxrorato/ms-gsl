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

	private static final List<String> NOMES_RESPONSAVEIS_SEGURADORA = Arrays.asList("Isabela Silva Costa", "Daniel Souza Lima",
			"Clara Pereira Silva", "Breno Castro Rodrigues", "Letícia Cardoso Oliveira", "Beatriz Fernandes Carvalho", 
			"Douglas Fernandes Souza", "Matheus Melo Santos", "Vitoria Souza Correia", "Carolina Ribeiro Cardoso");

	private static final List<String> NOMES_DESTINATARIOS = Arrays.asList("Letícia Rodrigues Alves", "Rafaela Pereira Cunha",
			"Fábio Correia Araujo", "Luan Ribeiro Santos", "Lara Barbosa Correia", "Emilly Barros Azevedo", "Giovanna Santos Pereira",
			"Gabriela Oliveira Ferreira", "Beatrice Goncalves Correias", "Luis Araujo Cavalcanti", "Brenda Ribeiro Rocha",
			"Felipe Azevedo Cardoso", "Tomás Ribeiro Sousa", "Arthur Goncalves Araujo", "Nicolash Correia Cavalcanti");
	
	private static final List<String> RAZOES_SOCIAIS_DESTINATARIAS = Arrays.asList("Laura e Mirella Doces & Salgados ME", "Marcela e Davi Telas Ltda",
			"Pedro e Tomás Ferragens Ltda", "Isabela e Raquel Esportes Ltda", "Carla e Kamilly Lavanderia ME", "Benício e Vitor Locações de Automóveis Ltda", 
			"Mariana e Larissa Vidros ME", "Nathan e Isabelly Joalheria Ltda", "Catarina e Nicolas Consultoria Financeira Ltda", "Alice e Clara Marcenaria Ltda", 
			"Giovanni e Lorenzo Mudanças ME", "Vitor e Allana Publicidade e Propaganda Ltda", "Stella e Carolina Telas Ltda", "Leandro e Teresinha Contábil Ltda", 
			"Joaquim e Sophia Corretores Associados Ltda");

	private static final List<String> CHAVES_ACESSOS_DACTE = Arrays.asList(
			"3.4444.55.555.444/1111-88-22-000-000.000.111-100.005.111-2",
			"1.2222.33.333.444/1111-22-33-000-000.000.111-222.333.444-5", 
			"2.9999.22.222.333/4444-99-11-000-000.000.111-500.007.888-3", 
			"4.8888.11.111.222/6666-77-22-000-000.000.555-700.008.333-1", 
			"6.3333.00.777.888/7777-66-55-000-000.000.666-800.009.222-0",
			"3.1111.33.333.444/2222-99-44-000-000.000.222-300.001.222-7", 
			"2.3333.44.444.555/7777-88-33-000-000.000.666-400.003.222-5", 
			"9.8888.77.777.666/5555-44-33-000-000.000.222-100.002.333-4", 
			"8.9999.11.111.333/3333-22-77-000-000.000.777-800.008.111-2",
			"7.6666.55.555.444/3333-66-11-000-000.000.444-600.007.222-8", 
			"4.5555.66.666.777/8888-99-88-000-000.000.777-300.006.555-4");

	private static final List<String> INSCRICOES_ESTADUAIS = Arrays.asList("771.830.777.387", "460.764.144.548", 
			"070.497.084.081", "625.893.500.921","379.612.640.301",	"521.206.260.205", "392.763.996.315", 
			"230.990.678.193", "308.389.638.380","703.596.718.830",	"265.479.074.369", "993.069.867.791",
			"323.396.757.533", "557.517.897.618", "258.237.487.910", "919.640.963.752", "630.303.753.203", 
			"906.517.528.161", "785.266.327.198", "318.589.950.890", "453.562.814.616", "745.031.505.353");
	
	private static final List<String> TELEFONES = Arrays.asList("(62)96119-8227", "(67)92325-6250", 
			"(11)99934-9401", "(83)99668-8502","(49)99113-9076", "(31)99261-8960", "(15)96669-5485", 
			"(11)92724-8631", "(11)96105-2277","(11)96761-9263", "(12)93827-9748", "(31)95223-6830");

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
		} while (distancia.compareTo(GeneralUtils.ZERO) == 0);
		return distancia;
	}
	
	public static BigDecimal getLatitudeLongitude(BigDecimal limit) {
		BigDecimal latitudeLongitude = GeneralUtils.ZERO;
		do {
			latitudeLongitude = RandomUtils.getRandomNumber(GeneralUtils.UM, limit, SCALE_4);
		} while (latitudeLongitude.compareTo(GeneralUtils.ZERO) == 0);
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
	
	public static LocalDateTime getDataPrevisaoEntrega(LocalDateTime dataSolicitacao, EnderecoOrigem enderecoOrigem, 
			EnderecoDestino enderecoDestino, BigDecimal distancia) {

		return dataSolicitacao.plusDays(getDaysPrevisaoEntrega(enderecoOrigem, 
				enderecoDestino, distancia))/*.toLocalDate()*/;
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

	public static String getNomeResponsavelSeguradora() {
		return NOMES_RESPONSAVEIS_SEGURADORA.get(RandomUtils.getRandomNumber(NOMES_RESPONSAVEIS_SEGURADORA.size() - 1));
	}
	public static String getChaveAcesoDacte() {
		return CHAVES_ACESSOS_DACTE.get(RandomUtils.getRandomNumber(CHAVES_ACESSOS_DACTE.size() - 1));
	}

	public static String getNomeDestinatario() {
		return NOMES_DESTINATARIOS.get(RandomUtils.getRandomNumber(NOMES_DESTINATARIOS.size() - 1));
	}

	public static String getRazaoSocialDestinatario() {
		return RAZOES_SOCIAIS_DESTINATARIAS.get(RandomUtils.getRandomNumber(RAZOES_SOCIAIS_DESTINATARIAS.size() - 1));
	}

	public static String getInscricaoEstadual() {
		return INSCRICOES_ESTADUAIS.get(RandomUtils.getRandomNumber(INSCRICOES_ESTADUAIS.size() - 1));
	}

	public static String getTelefone() {
		return TELEFONES.get(RandomUtils.getRandomNumber(TELEFONES.size() - 1));
	}
}
