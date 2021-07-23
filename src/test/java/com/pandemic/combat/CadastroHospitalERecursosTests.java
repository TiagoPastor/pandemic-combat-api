package com.pandemic.combat;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pandemic.combat.domain.model.Hospital;
import com.pandemic.combat.domain.service.HospitalService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroHospitalERecursosTests {
	
	
	@Autowired
	private HospitalService hospitalService;
	
	
	
	@Test
	public void testarCadastroComSucesso() {
		
		// cenário
		Hospital hospital = new Hospital();
		hospital.setNome("hospital teste");
		hospital.setBairro("bairro teste");
		hospital.setCidade("cidade teste");
		hospital.setCnpj("29.432.153/0001-90");
		hospital.setEstado("estado teste");
		hospital.setNumero("10A");
		hospital.setRua("rua teste");
		
		// ação
		hospital = hospitalService.salvar(hospital);
		
		// validação
		assertThat(hospital).isNotNull();
		assertThat(hospital.getId()).isNotNull();
		
	}
	
	
	@Test
	public void testarCadastroSemNome() {
		
		Hospital hospital = new Hospital();
		hospital.setNome(null);
		hospital.setBairro("bairro teste");
		hospital.setCidade("cidade teste");
		hospital.setCnpj("29.432.153/0001-90");
		hospital.setEstado("estado teste");
		hospital.setNumero("10A");
		hospital.setRua("rua teste");
		
		ConstraintViolationException erroEsperado =
				Assertions.assertThrows(ConstraintViolationException.class, () -> {
					hospitalService.salvar(hospital);
				});

		assertThat(erroEsperado).isNotNull();
				
	}

}
