package com.pandemic.combat.api.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecursoDTO {
	

	
	private Long id;
	
	@NotBlank
	private String item;
	
	@NotNull
	private int pontos;
	

}
