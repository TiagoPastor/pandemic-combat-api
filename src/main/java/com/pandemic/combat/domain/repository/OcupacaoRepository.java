package com.pandemic.combat.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pandemic.combat.domain.model.Ocupacao;

@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {
	
	@Query("from Ocupacao where hospital.id = :hospitalId")
	Ocupacao findByHospital(Long hospitalId);
	
	@Query("from Ocupacao where hospital.id = :hospitalId and percentual > 90")
	Ocupacao ocupacao90Porcento(Long hospitalId);
	
	@Query( "from Ocupacao where percentual > 90" )
	List<Ocupacao> findBySuperLotado();
	
	@Query( "from Ocupacao where percentual < 90" )
	List<Ocupacao> findByBaixaLotacao();

}
