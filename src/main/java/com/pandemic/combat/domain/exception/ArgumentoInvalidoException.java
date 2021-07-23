package com.pandemic.combat.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ArgumentoInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ArgumentoInvalidoException(String mensagem) {
		super(mensagem);

	}

}
