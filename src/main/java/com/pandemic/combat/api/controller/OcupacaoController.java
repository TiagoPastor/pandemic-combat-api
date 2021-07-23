package com.pandemic.combat.api.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pandemic.combat.api.model.dto.OcupacaoDTO;
import com.pandemic.combat.domain.exception.ArgumentoInvalidoException;
import com.pandemic.combat.domain.model.Ocupacao;
import com.pandemic.combat.domain.repository.OcupacaoRepository;
import com.pandemic.combat.domain.service.OcupacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/ocupacao")
@Api( value = "Ocupação", tags = "Ocupação" )
public class OcupacaoController {
	
	
	@Autowired
	OcupacaoRepository repository;
	
	@Autowired
	OcupacaoService ocupacaoService;
	
	
	
	@ApiOperation( value = "Buscar percentual da ocupação por hospital" )
	@GetMapping( "/hospital/{idHospital}" )
	public Ocupacao ocupacaoHospital( @PathVariable Long idHospital){
		return repository.findByHospital(idHospital);
	}
	
	
	@PutMapping("/hospital/{hospitalId}")
	@ApiOperation( value = "Atualizar a ocupação do hospital" )
	public Ocupacao atualizar(@PathVariable Long hospitalId,
			@RequestBody OcupacaoDTO ocupacaoDTO) {
		
		try {
			
			Ocupacao ocupacao = ocupacaoService.toDomainObject(ocupacaoDTO);
			
			Ocupacao ocupacaoAtual = repository.findByHospital(hospitalId);
			
			BeanUtils.copyProperties(ocupacao, ocupacaoAtual, "id", "hospital");
			
			return ocupacaoService.salvar(ocupacaoAtual);

		} catch (IllegalArgumentException e) {
			
			throw new ArgumentoInvalidoException( String.format( "Não existe um status para ser atualizado" ));
			
		}
		
		
	}

}
