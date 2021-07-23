package com.pandemic.combat.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pandemic.combat.api.model.dto.OcupacaoDTO;
import com.pandemic.combat.domain.model.Ocupacao;
import com.pandemic.combat.domain.repository.OcupacaoRepository;

@Service
public class OcupacaoService {
	
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	

	
	@Transactional
	public Ocupacao salvar( Ocupacao ocupacao ) {
		return ocupacaoRepository.save( ocupacao );
	}
	
	public Ocupacao toDomainObject( OcupacaoDTO ocupacaoDTO ) {
		Ocupacao ocupacao = new Ocupacao();
		ocupacao.setPercentual( ocupacaoDTO.getPercentual() );
		
		return ocupacao;
	}
	
	
	public OcupacaoDTO toDomainReverseObject( Ocupacao ocupacao ) {
		OcupacaoDTO ocupacaoDTO = new OcupacaoDTO();
		ocupacaoDTO.setPercentual( ocupacao.getPercentual() );
		
		return ocupacaoDTO;
	}

}
