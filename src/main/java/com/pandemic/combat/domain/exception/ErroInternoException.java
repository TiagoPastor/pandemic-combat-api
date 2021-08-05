package com.pandemic.combat.domain.exception;

public class ErroInternoException extends IllegalArgumentException{
	private static final long serialVersionUID = 1L;
	
	
	public ErroInternoException( String mensagem ) {
		super( mensagem );
	}
	
	public ErroInternoException( String mensagem, Throwable causa ) {
		super( mensagem, causa );
		
	}

}
