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


    @PostMapping
    public ResponseEntity<UUID> cadastrarConsulta(
            @RequestBody ConsultaDTO dto) {

        UUID id = consultaService.cadastrarConsulta(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(id);
    }
}
