package com.dev.gslentrega.request;

import com.dev.gslentrega.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DadosAtorCteRequest {

	private TipoDocumento tipoDocumento;
	private Long cpfCnpj;
	private String nomeRazaoSocial;
	private String inscricaoEstadual;
	private String telefone;
	private EnderecoAtorCteRequest endereco;
}
