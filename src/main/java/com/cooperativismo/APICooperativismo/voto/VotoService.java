package com.cooperativismo.APICooperativismo.voto;

import java.util.List;

public interface VotoService {

	/**
	 * Return all Votos in backend service
	 *
	 *
	 */
	public List<Voto> getVotos();
	
	/**
	 * Add new Voto in backend service
	 *
	 * @param the voto that will be inserted in DB.
	 */
	public void addNewVoto(Voto voto);
	
	/**
	 * Return one Voto in backend service
	 *
	 * @param the voto that will be returned from DB.
	 */
	public Long getVotosPauta(Long id);
}
