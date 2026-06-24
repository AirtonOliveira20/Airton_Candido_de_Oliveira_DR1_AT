package com.airton.airmed.service;

import com.airton.airmed.DTO.ConsultaDTO;
import com.airton.airmed.model.Consulta;
import com.airton.airmed.model.Medico;
import com.airton.airmed.model.Paciente;
import com.airton.airmed.model.StatusConsulta;
import com.airton.airmed.repository.ConsultaRepository;
import com.airton.airmed.repository.MedicoRepository;
import com.airton.airmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            PacienteRepository pacienteRepository,
            MedicoRepository medicoRepository) {

        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    private ConsultaDTO converterParaResponseDTO(Consulta consulta) {

        ConsultaDTO dto = new ConsultaDTO();

        dto.setIdPaciente(
                consulta.getPaciente().getIdPaciente());

        dto.setIdMedico(
                consulta.getMedico().getIdMedico());

        dto.setData(consulta.getData());
        dto.setObservacao(consulta.getObservacao());


        return dto;
    }


    public UUID cadastrarConsulta(ConsultaDTO dto) {

        Paciente paciente = pacienteRepository
                .findById(dto.getIdPaciente())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente não encontrado"));

        Medico medico = medicoRepository
                .findById(dto.getIdMedico())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Médico não encontrado"));

        Consulta consulta = new Consulta();

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setData(dto.getData());
        consulta.setObservacao(dto.getObservacao());

        consulta.setStatus(dto.getStatus() != null ? dto.getStatus() : StatusConsulta.AGENDADA
        );


        consultaRepository.save(consulta);

        return consulta.getIdConsulta();
    }


}