package com.airton.airmed.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idConsulta;

    @ManyToOne
    @JoinColumn(name = "idPaciente")
    private Paciente paciente;


    @ManyToOne
    @JoinColumn(name = "idMedico")
    private Medico medico;

    private LocalDate data;
    private LocalTime horario;
    private String observacao;
    private String status;

    @CreationTimestamp
    private LocalDateTime createAt;
}
