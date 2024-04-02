package br.com.start.api.assembler;

import org.modelmapper.AbstractConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class StringToLocalDateConverter extends AbstractConverter<String, LocalDate> {



    @Override
    public LocalDate convert(String source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(source, formatter);
    }



}
