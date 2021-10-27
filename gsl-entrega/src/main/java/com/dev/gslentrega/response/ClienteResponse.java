package com.dev.gslentrega.response;

import java.time.LocalDateTime;

import com.dev.gslentrega.enums.StatusCliente;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
