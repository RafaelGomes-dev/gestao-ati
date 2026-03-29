package com.Rafael.gestao_ati.repository;

import com.Rafael.gestao_ati.model.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Long> {
    boolean existsByNumeroSerie(String numeroSerie);
}
