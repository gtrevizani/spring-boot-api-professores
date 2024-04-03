package br.com.start.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.start.domain.model.Professor;
import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.api.dto.ProfessorSaidaDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {
    @Autowired
    private ModelMapper mapper;


    public void parseStringToLocalDate(String contratacao){
        mapper.addConverter(new StringToLocalDateConverter());
    }

    public ProfessorSaidaDto parseInputToOutput(ProfessorEntradaDto entradaDto) {

        return mapper.map(entradaDto, ProfessorSaidaDto.class);

    }
    public Professor parseOutputToEntity(ProfessorSaidaDto saidaDto) {
        return mapper.map(saidaDto, Professor.class);

    }

    public ProfessorSaidaDto parseEntityToOutput(Professor professor){
        return mapper.map(professor, ProfessorSaidaDto.class);
    }

    public void parseInputToEntity(ProfessorEntradaDto entradaDto, Professor professor) {
        mapper.map(entradaDto, professor);
    }

    public List<ProfessorSaidaDto> parseEntityListToOutputList(List<Professor> professores) {

//        return mapper.map(professores, new TypeToken<List<ProfessorSaidaDto>>() {}.getType());
        return professores.stream().map(professor -> mapper.map(professor, ProfessorSaidaDto.class)).collect(Collectors.toList());
    }

}