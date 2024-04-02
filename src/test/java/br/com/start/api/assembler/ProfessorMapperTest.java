package br.com.start.api.assembler;

import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.api.dto.ProfessorSaidaDto;
import br.com.start.domain.model.Professor;
import br.com.start.domain.repository.ProfessorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProfessorMapperTest {

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private ProfessorRepository repository;


    @Test
    public void parseProfessorSaidaDto_toProfessor(){

        ProfessorSaidaDto professor = Mockito.mock(ProfessorSaidaDto.class, Answers.RETURNS_MOCKS);

        Professor professor1 = professorMapper.parseProfessor(professor);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.getNome(), professor1.getNome());

    }

    @Test
    public void parseProfessor_toProfessorSaidaDtoSave(){

        Professor professor = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

        ProfessorSaidaDto professor1 = professorMapper.parseProfessorSaidaSave(professor);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.getNome(), professor1.getNome());

    }

    @Test
    public void parseProfessorEntradaDto_toProfessorSaidaDto(){
        ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);


        ProfessorSaidaDto professor1 = professorMapper.parseProfessorSaida(professor);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.getNome(), professor1.getNome());

    }

    @Test
    public void parseProfessorEntradaDto_toProfessorOptional(){

        ProfessorEntradaDto professor = Mockito.mock(ProfessorEntradaDto.class, Answers.RETURNS_MOCKS);
        Professor professor1 = Mockito.mock(Professor.class, Answers.RETURNS_MOCKS);

        professorMapper.parseProfessorOptional(professor, professor1);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.getNome(), professor1.getNome());

    }

    @Test
    public void parseListProfessor_toListProfessorSaidaDto(){
        List<Professor> professor = Collections.singletonList(Mockito.mock(Professor.class, Answers.RETURNS_MOCKS));

        List<ProfessorSaidaDto> professor1 = professorMapper.parseProfessorList(professor);

        Assertions.assertNotNull(professor1);
        Assertions.assertEquals(professor.size(), professor1.size());
    }

}
