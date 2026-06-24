package com.airton.airmed.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class PacienteDTO {


    private UUID idPaciente;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;



}
