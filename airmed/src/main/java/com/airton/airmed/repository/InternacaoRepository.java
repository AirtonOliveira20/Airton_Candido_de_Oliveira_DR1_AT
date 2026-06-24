package com.airton.airmed.repository;

import com.airton.airmed.model.Internacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InternacaoRepository  extends JpaRepository<Internacao, UUID> {
}
