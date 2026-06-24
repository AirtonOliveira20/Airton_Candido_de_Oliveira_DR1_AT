package com.airton.airmed.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idMedico;

    private String nome;
    private String crm;
    private String especialidade;

    @OneToMany (mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    @OneToMany (mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Internacao> internacoes;


}
