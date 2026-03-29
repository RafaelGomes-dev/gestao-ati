package com.Rafael.gestao_ati.repository;

import com.Rafael.gestao_ati.model.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, Long> {
    boolean existsByChave(String chave);
}