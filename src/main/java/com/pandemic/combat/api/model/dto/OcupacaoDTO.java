package com.pandemic.combat.api.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OcupacaoDTO {
	
	@NotNull
	private double percentual;
	
	
	
}
