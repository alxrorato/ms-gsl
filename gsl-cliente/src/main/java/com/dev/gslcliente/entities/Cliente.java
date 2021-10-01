package com.dev.gslcliente.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.dev.gslcliente.enums.StatusCliente;

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
@Entity
@Table(name = "tb_cliente", uniqueConstraints = 
	@UniqueConstraint(columnNames = "cnpj", name = "cnpj_uk"))
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@NotNull
	private Long cnpj;
	@NotNull
	private String razaoSocial;
	private String nomeComercial;
	private String email;
	private String telefone;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	@NotNull
	private LocalDateTime dataInclusao;
	private LocalDateTime dataExclusao;
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
}
