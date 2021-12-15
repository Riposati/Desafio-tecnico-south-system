package com.cooperativismo.APICooperativismo.associado;

import java.util.List;

public interface AssociadoService {

	public List<Associado> getAssociados();
	public void addNewAssociado(Associado associado);	
}
