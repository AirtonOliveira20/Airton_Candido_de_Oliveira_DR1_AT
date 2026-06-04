package com.airton.airmed.controller;


import com.airton.airmed.DTO.ConsultaDTO;
import com.airton.airmed.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public ResponseEntity<Object> listarConsultas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultaService.listarConsultas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarConsultaPorId(
            @PathVariable UUID id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultaService.buscarConsultaPorId(id));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarConsulta(
            @RequestBody ConsultaDTO dto) {

        UUID id = consultaService.cadastrarConsulta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarConsulta(
            @PathVariable UUID id,
            @RequestBody ConsultaDTO dto) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(consultaService.atualizarConsulta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(
            @PathVariable UUID id) {

        consultaService.deletarConsulta(id);

        return ResponseEntity.noContent().build();
    }
}
