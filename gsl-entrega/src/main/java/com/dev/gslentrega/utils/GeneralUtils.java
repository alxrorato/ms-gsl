package com.dev.gslentrega.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.dev.gslentrega.enums.TipoDocumento;
import com.dev.gslentrega.enums.UF;
import com.dev.gslentrega.response.DadosAtorCte;
import com.dev.gslentrega.response.EnderecoAtorCte;

public class GeneralUtils {
	
	public static final BigDecimal ZERO = new BigDecimal(0);
	public static final BigDecimal UM = new BigDecimal(1);
	public static final BigDecimal CEM = new BigDecimal(100);
	public static final BigDecimal DUZENTOS = new BigDecimal(200);
	public static final BigDecimal QUATROCENTOS = new BigDecimal(400);
	public static final BigDecimal SEISCENTOS = new BigDecimal(600);
	public static final BigDecimal OITOCENTOS = new BigDecimal(800);

	public static final String CPF = "CPF";
	public static final String CNPJ = "CNPJ";
	
	private static final Long CNPJ_BOA_ENTREGA = 44507211000190L;
	private static final String RAZAO_SOCIAL_BOA_ENTREGA = "BOA ENTREGA LTDA.";
	private static final String INSCRICAO_ESTADUAL_BOA_ENTREGA = "518.208.406.485";
	private static final String TELEFONE_BOA_ENTREGA = "(11)5773-8532";
	private static final String LOGRADOURO_BOA_ENTREGA = "Rua Duque de Caxias";
	private static final String NUMERO_BOA_ENTREGA = "225";
	private static final String COMPLEMENTO_BOA_ENTREGA = "Galpão";
	private static final String BAIRRO_BOA_ENTREGA = "CENTRO";
	private static final String CIDADE_BOA_ENTREGA = "SÃO PAULO";
	private static final UF UF_BOA_ENTREGA = UF.getUFBySigla("SP");
	private static final String CEP_BOA_ENTREGA = "01958-050";

	public static BigDecimal percentual(BigDecimal valorTotal, BigDecimal valorBase) {
		return valorBase.multiply(CEM).divide(valorTotal, 2, RoundingMode.HALF_UP);
	}

	public static DadosAtorCte getDadosBoaEntrega() {
		
		EnderecoAtorCte endereco = new EnderecoAtorCte();
		endereco.setLogradouro(LOGRADOURO_BOA_ENTREGA);
		endereco.setNumero(NUMERO_BOA_ENTREGA);
		endereco.setComplemento(COMPLEMENTO_BOA_ENTREGA);
		endereco.setBairro(BAIRRO_BOA_ENTREGA);
		endereco.setCidade(CIDADE_BOA_ENTREGA);
		endereco.setUf(UF_BOA_ENTREGA);
		endereco.setCep(CEP_BOA_ENTREGA);
		
		DadosAtorCte dados = new DadosAtorCte();
		dados.setTipoDocumento(TipoDocumento.CNPJ);
		dados.setCpfCnpj(CNPJ_BOA_ENTREGA);
		dados.setNomeRazaoSocial(RAZAO_SOCIAL_BOA_ENTREGA);
		dados.setInscricaoEstadual(INSCRICAO_ESTADUAL_BOA_ENTREGA);
		dados.setTelefone(TELEFONE_BOA_ENTREGA);
		dados.setEndereco(endereco);
		
		return dados;
		
	}
}
