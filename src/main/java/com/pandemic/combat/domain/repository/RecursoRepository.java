package com.pandemic.combat.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pandemic.combat.domain.model.Hospital;
import com.pandemic.combat.domain.model.Recurso;


@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
	
	List<Recurso> findByHospital( Hospital hospital );
	
	@Query( "from Recurso where hospital.id = :id")
	List<Recurso> findByIdHospital( Long id );
	
	@Query( "from Recurso where id in ( :listaId ) " )
	List<Recurso> findByListId( List<Long> listaId );
	

}
