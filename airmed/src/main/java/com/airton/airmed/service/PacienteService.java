package com.airton.airmed.service;

import com.airton.airmed.DTO.PacienteDTO;
import com.airton.airmed.model.Paciente;
import com.airton.airmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository repository){
        this.pacienteRepository = repository;
    }

    private PacienteDTO converterParaResponseDTO(Paciente paciente) {

        PacienteDTO dto = new PacienteDTO();

        dto.setId(paciente.getIdPaciente());
        dto.setNome(paciente.getNome());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setCpf(paciente.getCpf());
        dto.setTelefone(paciente.getTelefone());
        dto.setEmail(paciente.getEmail());
        dto.setEndereco(paciente.getEndereco());


        return dto;
    }


    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public UUID cadastrarPaciente(PacienteDTO dto){

        if (pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um paciente cadastrado com este CPF."
            );
        }

        Paciente paciente = new Paciente();

        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());
        paciente.setEndereco(dto.getEndereco());


        this.pacienteRepository.save(paciente);

        return paciente.getIdPaciente();
    }

    public PacienteDTO buscarPacientePorId(UUID id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));


        return converterParaResponseDTO(paciente);
    }

    public UUID atualizarPaciente(UUID id, PacienteDTO dto) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCpf(dto.getCpf());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());
        paciente.setEndereco(dto.getEndereco());

        pacienteRepository.save(paciente);

        return paciente.getIdPaciente();
    }

    public void deletarPaciente(UUID id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));

        pacienteRepository.delete(paciente);
    }



}
