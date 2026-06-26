package com.airton.airmed.repositories;

import com.airton.airmed.model.Paciente;
import com.airton.airmed.repository.PacienteRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PacienteRepositoryTest {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteRepositoryTest(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    @Test
    @DisplayName("Deve verificar se é possível salvar um paciente no banco de dados")
    void deveCadastrarPacienteNoBancoDeDados(){

        Paciente paciente = new Paciente(null,"Airton", LocalDate.of(1999, 5, 10), "111.111.111-39","21911111111",null,null);

        Paciente pacienteSalvo = this.pacienteRepository.save(paciente);

        assertNotNull(pacienteSalvo);
    }


}
