package com.pandemic.combat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.combat.api.model.dto.IntercambioDTO;
import com.pandemic.combat.domain.model.Recurso;
import com.pandemic.combat.domain.service.IntercambioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/intercambio")
@Api( value = "Intercâmbio", tags = "Intercâmbio" )
public class IntercambioController {
	
	
	@Autowired
	private IntercambioService intercambioService;
	
	
	@ApiOperation( value = "Trocar recursos entre hospitais" )
	@PutMapping("/hospital/{hospitalSolicitanteId}")
	public List<Recurso> trocarRecursos(@PathVariable Long hospitalSolicitanteId,
			@RequestBody IntercambioDTO intercambioDTO) {
		
		return intercambioService.cambiar(hospitalSolicitanteId, intercambioDTO);
		
	}

}
