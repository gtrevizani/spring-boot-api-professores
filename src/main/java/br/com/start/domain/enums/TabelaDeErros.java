package br.com.start.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

    CRIADO(HttpStatus.CREATED, ""),
    OK(HttpStatus.OK, ""),
    TEM_DADOS(HttpStatus.OK, "com dados"),
    SEM_DADOS(HttpStatus.NO_CONTENT, "sem dados"),
    ERRO_VALIDACAO(HttpStatus.BAD_REQUEST, "Dados da requisição incorretos"),
    EMAIL_JA_CADASTRADO(HttpStatus.PRECONDITION_FAILED, "E-mail já cadastrado"),
    PROFESSOR_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Professor não encontrado"),
    ERRO_SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "Sistema indisponível");


    private final HttpStatus httpStatus;
    private final String erro;
}
