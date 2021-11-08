package com.dev.gslentrega.response;

import com.dev.gslentrega.enums.TipoDocumento;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Dados de um ator do CT-e (emitente, remetente, destinatário, expedidor ou recebedor")
public class DadosAtorCte {

	@ApiModelProperty(name = "tipoDocumento", value = "Tipo do documento do ator do CT-e, que pode ser CPF ou CNPJ", 
			example = "CNPJ")
	private TipoDocumento tipoDocumento;

	@ApiModelProperty(name = "cpfCnpj", value = "CPF ou CNPJ do ator do CT-e", example = "13814889000192")
	private Long cpfCnpj;
	
	@ApiModelProperty(name = "nomeRazaoSocial", value = "Nome (se pessoa física) ou razão social (se pessoa jurídica) do ator do CT-e", 
			example = "BOA ENTREGA LTDA.")
	private String nomeRazaoSocial;
	
	@ApiModelProperty(name = "inscricaoEstadual", value = "Número da inscrição estadual do ator do CT-e", example = "518.208.406.485")
	private String inscricaoEstadual;
	
	@ApiModelProperty(name = "telefone", value = "Telefone do ator do CT-e", example = "(11)5773-8532")
	private String telefone;
	
	@ApiModelProperty(name = "endereco", value = "Endereço do ator do CT-e")
	private EnderecoAtorCte endereco;
}
