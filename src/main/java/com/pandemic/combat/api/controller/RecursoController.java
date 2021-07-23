package com.pandemic.combat.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.combat.domain.model.Recurso;
import com.pandemic.combat.domain.repository.RecursoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/recursos")
@Api( value = "Recursos", tags = "Recursos" )
public class RecursoController {

	
	@Autowired
	private RecursoRepository repository;
	
	
	@GetMapping( "/hospital/{idHospital}" )
	@ApiOperation( value = "Listar os recursos por hospital" )
	public List<Recurso> listarRecursosPorHospital( @PathVariable Long idHospital){
		return repository.findByIdHospital(idHospital);
	}
	

}
