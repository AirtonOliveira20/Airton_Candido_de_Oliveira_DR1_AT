package com.airton.airmed.controller;

import com.airton.airmed.DTO.PacienteDTO;
import com.airton.airmed.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PacienteController.class)
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class PacienteControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @MockitoBean
    private PacienteService service;

    @Autowired
    public PacienteControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    void deveCriarUmUsuarioComSucesso() throws Exception{

        //ARRANGE
        PacienteDTO pacienteDTO = new PacienteDTO(null,"Airton", LocalDate.of(1999, 5, 10), "111.111.111-39","21911111111");
        PacienteDTO pacienteDTOCriado = new PacienteDTO(UUID.randomUUID(),"Airton", LocalDate.of(1999, 5, 10), "111.111.111-39","21911111111");

        when(service.cadastrarPaciente(any(PacienteDTO.class))).thenReturn(pacienteDTOCriado);

        String jsonString = this.objectMapper.writeValueAsString(pacienteDTOCriado);


        MockHttpServletResponse response =  this.mockMvc.perform(post("/pacientes").contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)).andDo(print()).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getContentAsString()).contains("Airton");

    }

}
