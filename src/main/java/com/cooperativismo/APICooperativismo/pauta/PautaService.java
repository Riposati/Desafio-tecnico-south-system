package com.cooperativismo.APICooperativismo.pauta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

	private final PautaRepository pautaRepository;

	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	public List<Pauta> getPautas() {
		return pautaRepository.findAll();
	}

	public void addNewPauta(Pauta pauta) {
		Optional<Pauta> pautaOptional = pautaRepository.findPautaByDescricao(pauta.getDescricao());

		if (pautaOptional.isPresent()) {
			throw new IllegalStateException("Pauta já aberta!");
		}
		pautaRepository.save(pauta);
	}

	public Optional<Pauta> getPauta(Long id) {
		
		Optional<Pauta> pautaOptional = pautaRepository.findById(id);
		
		if(pautaOptional.isPresent()) {
			return pautaOptional;
		}else {
			throw new IllegalStateException("Pauta não existe!");
		}
		
//		try {
//			return pautaRepository.findById(id);
//		} catch (ApiException apiException) {
//			throw apiException;
//		} catch (Exception exception) {
//			throw new ApiException(exception.getMessage(), exception);
//		}
	}
}
