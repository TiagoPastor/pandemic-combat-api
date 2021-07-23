package com.pandemic.combat.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pandemic.combat.domain.model.Hospital;


@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
