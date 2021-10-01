package com.dev.gslentrega.response;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.dev.gslentrega.enums.StatusCliente;

public class ClienteResponse {

	private Long id;
	private Long cnpj;
	private String razaoSocial;
	private String nomeComercial;
	private String email;
	private String telefone;
	private StatusCliente status;
	private LocalDateTime dataInclusao;
	private LocalDateTime dataExclusao;
	private EnderecoClienteResponse endereco;
	
}
