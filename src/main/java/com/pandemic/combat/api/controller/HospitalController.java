package com.pandemic.combat.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.combat.api.model.dto.HospitalDTO;
import com.pandemic.combat.domain.model.Hospital;
import com.pandemic.combat.domain.repository.HospitalRepository;
import com.pandemic.combat.domain.service.HospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping( "/api/v1/hospitais" )
@Api( value = "Hospital", tags = "Hospital" )
public class HospitalController {
	
	
	@Autowired
	private HospitalRepository repository;
	
	@Autowired
	private HospitalService hospitalService;
	
	
	
	@ApiOperation( value = "Buscar todos os hospitais cadastrados" )
	@GetMapping
	public List<Hospital> listar(){
		return repository.findAll();
	}
	
	
	@ApiOperation( value = "Buscar hospital pelo ID" )
	@GetMapping( "/{id}" )
	public Optional<Hospital> buscarPorId( @PathVariable Long id ) {
		return repository.findById( id );
		
	}
	
	
	@ApiOperation( value = "Adicionar hospital com seus recursos e percentual de sua ocupação" )
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public Hospital adicionar( @RequestBody HospitalDTO hospitalDTO ) {
		return hospitalService.adicionarHospitalERecursos( hospitalDTO );
	}
	
	
}
