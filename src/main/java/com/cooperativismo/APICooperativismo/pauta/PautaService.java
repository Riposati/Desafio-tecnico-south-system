package com.cooperativismo.APICooperativismo.pauta;

import java.util.List;
import java.util.Optional;

public interface PautaService {

	/**
	 * Return all Pautas in backend service
	 *
	 *
	 */
	public List<Pauta> getPautas();

	/**
	 * Add new Pauta in backend service
	 *
	 * @param the pauta that will be inserted in DB.
	 */
	public void addNewPauta(Pauta pauta);

	/**
	 * Return one Pauta in backend service
	 *
	 * @param the pauta that will be returned from DB.
	 */
	public Optional<Pauta> getPauta(Long id);

}
