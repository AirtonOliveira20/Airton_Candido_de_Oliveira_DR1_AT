package com.airton.airmed.service;

import com.airton.airmed.DTO.MedicoDTO;
import com.airton.airmed.model.Medico;
import com.airton.airmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    private MedicoDTO converterParaResponseDTO(Medico medico) {

        MedicoDTO dto = new MedicoDTO();

        dto.setIdMedico(medico.getIdMedico());
        dto.setNome(medico.getNome());
        dto.setCrm(medico.getCrm());
        dto.setEspecialidade(medico.getEspecialidade());
        return dto;
    }

    public List<MedicoDTO> listarMedicos() {

        return medicoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }


    public UUID cadastrarMedico(MedicoDTO dto) {

        if (medicoRepository.existsByCrm(dto.getCrm())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "CRM já cadastrado"
            );
        }

        Medico medico = new Medico();

        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEspecialidade(dto.getEspecialidade());

        medicoRepository.save(medico);

        return medico.getIdMedico();
    }


}