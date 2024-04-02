package br.com.start.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.start.api.controller.ProfessorController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.start.api.dto.ProfessorEntradaDto;
import br.com.start.domain.service.ProfessorService;

@WebMvcTest(ProfessorController.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProfessorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProfessorService service;
    @InjectMocks
    private ProfessorController controller;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void criar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createProfessor_Valid())))
                .andExpect(status().isCreated());


    }
    @Test
    public void alterar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/professores/id/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(putProfessor_Valid())))
                .andExpect(status().isOk());


    }
    @Test
    public void listar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/professores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
    @Test
    public void consultar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/professores/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }
    @Test
    public void deletar() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/professores/id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    private ProfessorEntradaDto createProfessor_Valid(){
        ProfessorEntradaDto professor = new ProfessorEntradaDto();
        professor.setNome("Guilherme");
        professor.setEmail("teste@hotmail.com");
        professor.setFormacao("DOUTOR");
        professor.setDisciplina("Matemática");
        professor.setContratacao("29/02/2024");
        professor.setSalario("3000");
        return professor;
    }
    private ProfessorEntradaDto putProfessor_Valid(){
        ProfessorEntradaDto professor = new ProfessorEntradaDto();
        professor.setNome("Guilherme");
        professor.setEmail("testea@hotmail.com");
        professor.setFormacao("DOUTOR");
        professor.setDisciplina("Matemática");
        professor.setContratacao("29/02/2024");
        professor.setSalario("3000");
        return professor;
    }
}