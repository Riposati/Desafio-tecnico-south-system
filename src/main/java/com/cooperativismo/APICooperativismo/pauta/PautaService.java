package com.cooperativismo.APICooperativismo.pauta;

import java.util.List;
import java.util.Optional;

public interface PautaService {

	public List<Pauta> getPautas();
	public void addNewPauta(Pauta pauta);
	public Optional<Pauta> getPauta(Long id);
	
}
