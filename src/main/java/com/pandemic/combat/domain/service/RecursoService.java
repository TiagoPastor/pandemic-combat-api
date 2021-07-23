package com.pandemic.combat.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pandemic.combat.api.model.dto.RecursoDTO;
import com.pandemic.combat.domain.model.Recurso;
import com.pandemic.combat.domain.repository.RecursoRepository;

@Service
public class RecursoService {
	
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	
	@Transactional
	public Recurso salvar( Recurso recurso ) {
		return recursoRepository.save( recurso );
	}
	
	
	public List<Recurso> toDomainObject( List<RecursoDTO> recursosDTO ) {
		List<Recurso> recursos = new ArrayList<>();
		for( RecursoDTO recursoDTO: recursosDTO ) {
			
			Recurso recurso = new Recurso();
			recurso.setId( recursoDTO.getId() != null ? recursoDTO.getId() : null );
			recurso.setItem( recursoDTO.getItem() != null ? recursoDTO.getItem() : null );
			recurso.setPontos( recursoDTO.getPontos() != 0 ? recursoDTO.getPontos() : 0 );
			
			recursos.add( recurso );
		}
		
		return recursos;
	}
	
	public boolean recursoExiste( Long recursoId ) {
		return recursoRepository.existsById( recursoId );
	}
	
	public List<Recurso> listarRecursosPorId( List<Long> recursosId ){
		return recursoRepository.findByListId(recursosId);
	}

}
