package com.airton.airmed.controller;

import com.airton.airmed.DTO.MedicoDTO;
import com.airton.airmed.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarMedicos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.listarMedicos());
    }


    @PostMapping
    public ResponseEntity<UUID> cadastrarMedico(@RequestBody MedicoDTO dto) {

        UUID id = medicoService.cadastrarMedico(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

}
