package com.cooperativismo.APICooperativismo.voto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cooperativismo.APICooperativismo.associado.Associado;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findVotoByAssociado(Associado associado);
	
//	SELECT count(*) FROM `voto` WHERE id_pauta = ? and voto = 1
	@Query("select count(*) from Voto v where v.pauta.idPauta = ?1 and v.voto = 1")
	public Long findAllEnabled(Long id);
}
