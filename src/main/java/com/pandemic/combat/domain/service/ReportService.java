package com.pandemic.combat.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pandemic.combat.api.model.dto.HospitalDTO;
import com.pandemic.combat.domain.model.Ocupacao;
import com.pandemic.combat.domain.repository.OcupacaoRepository;



@Service
public class ReportService {
		
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	
	
	
	public List<HospitalDTO> hospitaisSuperLotado() {
		
		return toDomainListObject( ocupacaoRepository.findBySuperLotado() );
			
	}
	
	
	public List<HospitalDTO> hospitaisBaixaLotado() {
		
		return toDomainListObject( ocupacaoRepository.findByBaixaLotacao() );
			
	}
	
	
    public List<HospitalDTO> toDomainListObject( List<Ocupacao> listaDeOcupacao ) {
    	
    	List<HospitalDTO> hospitaisDTO = new ArrayList<>();
		
		for(Ocupacao ocupacao : listaDeOcupacao) {
			
			HospitalDTO hospitalDTO = new HospitalDTO();
			hospitalDTO.setBairro(ocupacao.getHospital().getBairro());
			hospitalDTO.setCidade(ocupacao.getHospital().getCidade());
			hospitalDTO.setCnpj(ocupacao.getHospital().getCnpj());
			hospitalDTO.setEstado(ocupacao.getHospital().getEstado());
			hospitalDTO.setNome(ocupacao.getHospital().getNome());
			hospitalDTO.setNumero(ocupacao.getHospital().getNumero());
			hospitalDTO.setRua(ocupacao.getHospital().getRua());
			hospitalDTO.setPercentual(ocupacao.getPercentual());
			
			hospitaisDTO.add(hospitalDTO);
			
		}
		
		return hospitaisDTO;
	}

}
