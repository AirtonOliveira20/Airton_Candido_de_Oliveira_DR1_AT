package com.airton.airmed.controller;

import com.airton.airmed.DTO.MedicoDTO;
import com.airton.airmed.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> listarMedicos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.listarMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarMedicoPorId(
            @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.buscarMedicoPorId(id));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarMedico(
            @RequestBody MedicoDTO dto) {

        UUID id = medicoService.cadastrarMedico(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarMedico(
            @PathVariable UUID id,
            @RequestBody MedicoDTO dto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.atualizarMedico(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedico(
            @PathVariable UUID id) {

        medicoService.deletarMedico(id);

        return ResponseEntity.noContent().build();
    }
}
