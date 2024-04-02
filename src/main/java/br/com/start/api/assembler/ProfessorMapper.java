package br.com.start.api.assembler;

import br.com.start.domain.repository.ProfessorRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.start.domain.model.Professor;
import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.api.dto.ProfessorSaidaDto;

import java.util.List;

@Component
public class ProfessorMapper {
    @Autowired
    private ProfessorRepository repository;
    @Autowired
    private ModelMapper mapper;


    public void parseLocalDate(String contratacao){
        mapper.addConverter(new StringToLocalDateConverter());
    }

    public ProfessorSaidaDto parseProfessorSaida(ProfessorEntradaDto entradaDto) {

        return mapper.map(entradaDto, ProfessorSaidaDto.class);

    }
    public Professor parseProfessor(ProfessorSaidaDto saidaDto) {
        return mapper.map(saidaDto, Professor.class);

    }

    public ProfessorSaidaDto parseProfessorSaidaSave(Professor professor){
        return mapper.map(professor, ProfessorSaidaDto.class);
    }

    public void parseProfessorOptional(ProfessorEntradaDto entradaDto, Professor professor) {
        mapper.map(entradaDto, professor);
    }

    public List<ProfessorSaidaDto> parseProfessorList(List<Professor> professor) {
        return mapper.map(professor, new TypeToken<List<ProfessorSaidaDto>>() {}.getType());
    }

}
