package com.cooperativismo.APICooperativismo.voto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
	
	// SELECT count(*) FROM `voto` WHERE voto.id_associado = ? and voto.id_pauta = ?
	@Query("select count(*) from Voto v where v.associado.idAssociado = ?1 and v.pauta.idPauta = ?2")
	public Long findIfAlreadyVoted(Long idAssociado, Long idPauta);	
	
	//	SELECT count(*) FROM `voto` WHERE id_pauta = ? and voto = 1
	@Query("select count(*) from Voto v where v.pauta.idPauta = ?1 and v.voto = 1")
	public Long findAllEnabled(Long id);
}
