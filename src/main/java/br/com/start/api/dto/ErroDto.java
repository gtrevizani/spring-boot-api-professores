package br.com.start.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String erro;
	private List<String> validacoes;
	
}
