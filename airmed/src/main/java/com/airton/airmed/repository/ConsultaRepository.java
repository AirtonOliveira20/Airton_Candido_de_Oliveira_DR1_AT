package com.airton.airmed.repository;

import com.airton.airmed.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {



}
