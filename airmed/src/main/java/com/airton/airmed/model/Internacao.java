package com.airton.airmed.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_internacao")
public class Internacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idInternacao;

    private LocalDate dataEntrada;

    private LocalDate dataAlta;

    private String quartoDeInternacao;

    @ManyToOne
    @JoinColumn(name = "idMedico")
    private Medico medico;
}
