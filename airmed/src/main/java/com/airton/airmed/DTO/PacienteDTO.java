package com.airton.airmed.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {


    private UUID idPaciente;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String telefone;


}
