package com.airton.airmed.services;

import com.airton.airmed.DTO.MedicoDTO;
import com.airton.airmed.model.Medico;
import com.airton.airmed.repository.MedicoRepository;
import com.airton.airmed.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {
    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;


    private Medico criarMedicoValido(){

        Medico medico = new Medico();

        UUID idMedico = UUID.randomUUID();

        medico.setIdMedico(idMedico);
        medico.setNome("Dr. Humberto");
        medico.setCrm("123456");
        medico.setEspecialidade("Cardiologista");


        return medico;

    }

    private MedicoDTO criarMedicoDTOValido() {

        MedicoDTO dto = new MedicoDTO();

        dto.setNome("Dr. Humberto");
        dto.setCrm("123456");
        dto.setEspecialidade("Cardiologista");

        return dto;
    }

    @Test
    void deveCadastrarMedico() {

        MedicoDTO dto = criarMedicoDTOValido();
        Medico medicoSalvo = criarMedicoValido();

        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoSalvo);

        UUID resultado = medicoService.cadastrarMedico(dto);

        assertNotNull(resultado);

        assertEquals(medicoSalvo.getIdMedico(), resultado);

        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

}
