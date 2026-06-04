package com.airton.airmed.repository;

import com.airton.airmed.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {

    boolean existsByCpf(String cpf);
}
