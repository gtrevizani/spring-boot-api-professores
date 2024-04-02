package br.com.start.domain.repository;

import br.com.start.api.assembler.ProfessorMapper;
import br.com.start.domain.enums.TipoFormacao;
import br.com.start.domain.model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository repository;

    @InjectMocks
    private ProfessorMapper assembler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testProfessor_whenSaved_returnProfessorSaved() {
        //Given

        Professor professor = createProfessor();

        //When
        Professor professorSaved = this.repository.save(professor);


        //Then / Assert
        assertNotNull(repository.findById(professor.getId()));
        assertEquals(professor.getEmail(), professorSaved.getEmail());
    }

    @Test
    void testProfessor_whenSavedNameIsEmpty_returnProfessorException() {
        //Given
        Professor professor = new Professor();

//        Assert
        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> this.repository.save(professor));

    }

    @Test
    void testProfessor_whenUpdate_returnProfessorUpdated() {
        Professor professor = createProfessor();

        Professor professorSaved = this.repository.save(professor);
        professorSaved.setNome("Trevs");

        Professor professorUpdated = this.repository.save(professorSaved);

        assertNotNull(professorUpdated);
        assertNotNull(professorUpdated.getId());
        assertEquals(professorSaved.getNome(), professorUpdated.getNome());
    }

    @Test
    void testProfessor_whenDelete_returnProfessorDeleted() {
        Professor professor = createProfessor();

        Professor professorSaved = this.repository.save(professor);

        this.repository.delete(professorSaved);

        Optional<Professor> professorOptional = repository.findById(professorSaved.getId());

        assertTrue(professorOptional.isEmpty());

    }

    @Test
    void testProfessor_whenEmailExists_returnTrue() {

        Professor professor = createProfessor();

        //Whenn
        Professor professorSaved = this.repository.save(professor);

        //Assert
        assertTrue(repository.existsByEmail(professor.getEmail()));
        assertEquals(professor.getEmail(), professorSaved.getEmail());

    }

    @Test
    void testProfessor_whenFindAll_thenReturnProfessorListSize() {
        //Given

        Professor professor = createProfessor();


        //When
        Professor professorSaved = this.repository.save(professor);


        //Then / Assert
        assertNotNull(repository.findAll());
        assertEquals(professor.getId(), professorSaved.getId());
        assertEquals(1, repository.findAll().size());

    }

    private Professor createProfessor() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setNome("Guilherme");
        professor.setEmail("testea@hotmail.com");
        professor.setFormacao(TipoFormacao.DOUTOR);
        professor.setDisciplina("Matemática");
        professor.setContratacao(LocalDate.of(2002, 2, 22));
        professor.setSalario(new BigDecimal(200));

        return professor;
    }

}