package com.pandemic.combat.api.model.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalDTO {
	
	
	@NotBlank
	private String nome;

	@NotBlank
	private String estado;

	@NotBlank
	private String cidade;

	@NotBlank
	private String bairro;

	@NotBlank
	private String rua;

	@NotBlank
	private String numero;

	@NotBlank
	private String cnpj;
	
	@Valid
	@NotNull
	private List<RecursoDTO> recurso;
	
	@Valid
	@NotNull
	private OcupacaoDTO ocupacao;
	
	private double percentual;

}
