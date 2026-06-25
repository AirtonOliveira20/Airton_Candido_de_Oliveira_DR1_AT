package com.airton.airmed.unitarios;

import com.airton.airmed.DTO.PacienteDTO;
import com.airton.airmed.model.Paciente;
import com.airton.airmed.repository.PacienteRepository;
import com.airton.airmed.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;


    private Paciente criarPacienteValido(){

        Paciente paciente = new Paciente();

        UUID idPaciente = UUID.randomUUID();

        paciente.setIdPaciente(idPaciente);
        paciente.setNome("Airton");
        paciente.setDataNascimento(LocalDate.of(2000, 5, 10));
        paciente.setCpf("999.999.999-36");
        paciente.setTelefone("21911111111");

        return paciente;

    }

    private PacienteDTO criarPacienteDTOValido() {

        PacienteDTO dto = new PacienteDTO();

        dto.setNome("Airton");
        dto.setDataNascimento(LocalDate.of(1999, 5, 10));
        dto.setCpf("999.999.999-36");
        dto.setTelefone("21911111111");

        return dto;
    }

    @Test
    void deveCadastrarPaciente(){

        PacienteDTO dto = criarPacienteDTOValido();
        Paciente pacienteSalvo = criarPacienteValido();

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteSalvo);

        UUID resultado = pacienteService.cadastrarPaciente(dto);

        assertNotNull(resultado);
        assertEquals(pacienteSalvo.getIdPaciente(), resultado);

        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void deveBuscarPorIdQuandoExistir(){
        Paciente paciente = criarPacienteValido();
        UUID idPaciente = paciente.getIdPaciente();

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(paciente));

        PacienteDTO resultado = pacienteService.buscarPacientePorId(idPaciente);

        assertEquals(idPaciente, resultado.getIdPaciente());

        verify(pacienteRepository).findById(idPaciente);

    }

    @Test
    void deveExcluirPaciente(){
        Paciente paciente = criarPacienteValido();
        UUID idPaciente = paciente.getIdPaciente();

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.of(paciente));

        pacienteService.deletarPaciente(idPaciente);

        verify(pacienteRepository, times(1)).findById(idPaciente);

        verify(pacienteRepository, times(1)).delete(paciente);
    }

    @Test
    void deveValidarComportamentoQuandoPacienteNaoExiste(){
        UUID idPaciente = UUID.randomUUID();

        when(pacienteRepository.findById(idPaciente)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> pacienteService.buscarPacientePorId(idPaciente));

        assertEquals(HttpStatus.NOT_FOUND,exception.getStatusCode());

        verify(pacienteRepository, times(1)).findById(idPaciente);

    }
}
