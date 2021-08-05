package com.pandemic.combat.domain.exception;

public class OcupacaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;
	
	
	public OcupacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	
	}
	
	public OcupacaoNaoEncontradaException(Long hospitalId) {
		this(String.format( "Não existe um status para o hospital %d", hospitalId ));
	
	}


}
