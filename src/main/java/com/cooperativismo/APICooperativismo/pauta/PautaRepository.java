package com.cooperativismo.APICooperativismo.pauta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

	Optional<Pauta> findPautaByDescricao(String descricao);
}
