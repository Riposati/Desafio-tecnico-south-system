package com.cooperativismo.APICooperativismo.voto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooperativismo.APICooperativismo.associado.Associado;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findVotoByAssociado(Associado associado);
	
}
