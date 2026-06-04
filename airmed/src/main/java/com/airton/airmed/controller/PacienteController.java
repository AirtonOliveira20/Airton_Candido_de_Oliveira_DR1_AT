package com.airton.airmed.controller;

import com.airton.airmed.DTO.PacienteDTO;
import com.airton.airmed.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<Object> listarPacientes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.listarPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPacientePorId(
            @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.buscarPacientePorId(id));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarPaciente(
            @RequestBody PacienteDTO dto) {

        UUID id = pacienteService.cadastrarPaciente(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarPaciente(
            @PathVariable UUID id,
            @RequestBody PacienteDTO dto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.atualizarPaciente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(
            @PathVariable UUID id) {

        pacienteService.deletarPaciente(id);

        return ResponseEntity.noContent().build();
    }
}