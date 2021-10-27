package com.dev.gslentrega.response;

import com.dev.gslentrega.enums.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DadosAtorCte {

	private TipoDocumento tipoDocumento;
	private Long cpfCnpj;
	private String nomeRazaoSocial;
	private String inscricaoEstadual; //Ex.: 518.208.406.485
	private String telefone;
	private EnderecoAtorCte endereco;
}
