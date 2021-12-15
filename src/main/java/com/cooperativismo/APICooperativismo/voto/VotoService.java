package com.cooperativismo.APICooperativismo.voto;

import java.util.List;

public interface VotoService {

	public List<Voto> getVotos();
	public void addNewVoto(Voto voto);
	public Long getVotosPauta(Long id);
}
