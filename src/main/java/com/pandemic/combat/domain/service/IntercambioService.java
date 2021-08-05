package com.pandemic.combat.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pandemic.combat.api.model.dto.IntercambioDTO;
import com.pandemic.combat.api.model.dto.RecursoDTO;
import com.pandemic.combat.domain.exception.EntidadeNaoEncontradaException;
import com.pandemic.combat.domain.model.Hospital;
import com.pandemic.combat.domain.model.Ocupacao;
import com.pandemic.combat.domain.model.Recurso;
import com.pandemic.combat.domain.repository.HospitalRepository;
import com.pandemic.combat.domain.repository.OcupacaoRepository;
import com.pandemic.combat.domain.repository.RecursoRepository;


@Service
public class IntercambioService {
	
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	
	
	public List<Recurso> cambiar( Long idHospitalSolicitante, IntercambioDTO intercambioDTO ) {
		List<Recurso> recursosAtualizados = new ArrayList<>();

		if ( validarIntercambio( idHospitalSolicitante, intercambioDTO )) {

			Optional<Hospital> hospitalSolicitante = hospitalRepository.findById( idHospitalSolicitante );
			Optional<Hospital> hospitalSolicitado = hospitalRepository.findById( intercambioDTO.getHospitalSolicitadoId() );

			List<Long> listaIdSolicitate = new ArrayList<>();
			intercambioDTO.getRecursosSolicitante().forEach( recursoDTO->listaIdSolicitate.add(recursoDTO.getId()) );
			
			List<Long> listaIdSolicitado = new ArrayList<>();
			intercambioDTO.getRecursosSolicitado().forEach( recursoDTO->listaIdSolicitado.add(recursoDTO.getId()) );
			

			recursosAtualizados = transferirRecurso( hospitalSolicitante.get(), hospitalSolicitado.get(), 
					recursoRepository.findByListId(listaIdSolicitate), recursoRepository.findByListId(listaIdSolicitado) );

		} 
		
		return recursosAtualizados;

	}
	
	
	
	@Transactional
	private List<Recurso> transferirRecurso( Hospital hospitalSolicitante, Hospital hospitalSolicitado,
			List<Recurso> recursosHospitalSolicitante, List<Recurso> recursosHospitalSolicitado ) {
		
		
		recursosHospitalSolicitante.forEach( recurso->recurso.setHospital(hospitalSolicitado) );
		
		recursoRepository.saveAll( recursosHospitalSolicitante );
		
		recursosHospitalSolicitado.forEach( recurso->recurso.setHospital( hospitalSolicitante ) );
		
		recursoRepository.saveAll( recursosHospitalSolicitado );
		
		return recursosHospitalSolicitado;
				
	}
	
	
	private boolean validarIntercambio( Long idHospitalSolicitante, IntercambioDTO intercambioDTO ) {
		Long pontuacaoHospitalSolicitante = 0L;
		Long pontuacaoHospitalSolicitado = 0L;
		boolean intercambioValidado = false;
		
		pontuacaoHospitalSolicitante = pontuacao( intercambioDTO.getRecursosSolicitante() );
		pontuacaoHospitalSolicitado = pontuacao( intercambioDTO.getRecursosSolicitado() );
		
		if( pontuacaoHospitalSolicitante.equals( pontuacaoHospitalSolicitado )) {
			
			intercambioValidado = true;
			
		}else {
			
			intercambioValidado = ocupacaoMaiorQue90Porcento( idHospitalSolicitante, 
					intercambioDTO.getHospitalSolicitadoId() );
			
		}
		
		return intercambioValidado;
	}
	
	
	
	private Long pontuacao( List<RecursoDTO> recursosDTO ) {
		Long pontuacao = 0L;
		
		//try {
			
			for( RecursoDTO recursoDTO : recursosDTO ) {
				
				Optional<Recurso> recurso = recursoRepository.findById( recursoDTO.getId() );				
				pontuacao += recurso.get().getPontos();
			}
			
			return pontuacao;
			
		//} catch (NoSuchElementException e) {
			//throw new EntidadeNaoEncontradaException( String.format( "O recurso não existe na base de dados" ) );
			
		//}
		
		
	}
	
	
		
	private boolean ocupacaoMaiorQue90Porcento( Long idHospitalSolicitante, Long idHospitalSolicitado ) {
		boolean ocupacaoMaiorQue90Porcento = false;
		Ocupacao ocupacaoHospitalSolicitante = ocupacaoRepository.ocupacao90Porcento
				( idHospitalSolicitante );
		
		Ocupacao ocupacaoHospitalSolicitado = ocupacaoRepository.ocupacao90Porcento
				( idHospitalSolicitado );
		
		if( ocupacaoHospitalSolicitante != null || ocupacaoHospitalSolicitado != null ) {
			
			ocupacaoMaiorQue90Porcento = true;			
		}
		
		return ocupacaoMaiorQue90Porcento;
				
	}
	
	

}
