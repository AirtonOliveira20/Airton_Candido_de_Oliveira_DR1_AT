package com.airton.airmed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPaciente;

    private String nome;
    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String telefone;

    @OneToMany (mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    @CreationTimestamp
    private LocalDateTime createAt;





}
