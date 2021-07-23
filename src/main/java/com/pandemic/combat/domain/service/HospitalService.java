package com.pandemic.combat.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pandemic.combat.api.model.dto.HospitalDTO;
import com.pandemic.combat.domain.model.Hospital;
import com.pandemic.combat.domain.model.Ocupacao;
import com.pandemic.combat.domain.model.Recurso;
import com.pandemic.combat.domain.repository.HospitalRepository;
import com.pandemic.combat.domain.repository.OcupacaoRepository;
import com.pandemic.combat.domain.repository.RecursoRepository;

@Service
public class HospitalService {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private RecursoService recursoService;
	
	@Autowired
	private OcupacaoService ocupacaoService;
	
	
	@Transactional
	public Hospital adicionarHospitalERecursos( HospitalDTO hospitalDTO ) {
		Hospital hospital = toDomainObject( hospitalDTO );
		hospitalRepository.save( hospital );
		
		List<Recurso> recursos = recursoService.toDomainObject( hospitalDTO.getRecurso() );
		for( Recurso recurso : recursos ) {
			
			recurso.setHospital( hospital );
			recursoRepository.save( recurso );
		}
		
		Ocupacao ocupacao = ocupacaoService.toDomainObject( hospitalDTO.getOcupacao() );
		ocupacao.setHospital( hospital );
		ocupacaoRepository.save( ocupacao );
		
		
		return hospital;
	}
	
	@Transactional
	public Hospital salvar( Hospital hospital ) {
		
		return hospitalRepository.save( hospital );		
	}
	
	
	public Hospital toDomainObject( HospitalDTO hospitalDTO ) {
		Hospital hospital = new Hospital();
		hospital.setNome(hospitalDTO.getNome());
		hospital.setEstado(hospitalDTO.getEstado());
		hospital.setCidade(hospitalDTO.getCidade());
		hospital.setBairro(hospitalDTO.getBairro());
		hospital.setRua(hospitalDTO.getRua());
		hospital.setNumero(hospitalDTO.getNumero());
		hospital.setCnpj(hospitalDTO.getCnpj());
		
		return hospital;
	}

}
