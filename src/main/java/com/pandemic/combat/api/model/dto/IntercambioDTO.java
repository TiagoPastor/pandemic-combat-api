package com.pandemic.combat.api.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class IntercambioDTO {
	
	
	private List<RecursoDTO> recursosSolicitante;
	
	private Long hospitalSolicitadoId;
	
	private List<RecursoDTO> recursosSolicitado;
	

}
