package com.airton.airmed.DTO;

import com.airton.airmed.model.StatusConsulta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class ConsultaDTO {

    private UUID idPaciente;
    private UUID idMedico;

    private LocalDate data;
    private LocalTime horario;
    private String observacao;
    private StatusConsulta status;


}