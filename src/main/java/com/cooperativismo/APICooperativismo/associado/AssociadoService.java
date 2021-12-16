package com.cooperativismo.APICooperativismo.associado;

import java.util.List;

public interface AssociadoService {

	/**
	 * Return all Associados in backend service
	 *
	 *
	 */
	public List<Associado> getAssociados();
	
	/**
	 * Add new Associado in backend service
	 *
	 * @param the associado that will be inserted in DB.
	 */
	public void addNewAssociado(Associado associado);	
}
