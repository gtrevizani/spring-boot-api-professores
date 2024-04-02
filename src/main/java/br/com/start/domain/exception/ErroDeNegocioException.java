package br.com.start.domain.exception;

import br.com.start.domain.enums.TabelaDeErros;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ErroDeNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final HttpStatus httpStatus;
	private final String erro;

	public ErroDeNegocioException(TabelaDeErros tabela){
		this.httpStatus = tabela.getHttpStatus();
		this.erro = tabela.getErro();
	}
	
}
