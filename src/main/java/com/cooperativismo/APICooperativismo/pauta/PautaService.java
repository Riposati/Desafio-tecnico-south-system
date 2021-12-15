package com.cooperativismo.APICooperativismo.pauta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperativismo.APICooperativismo.tratamentoErros.ApiException;

@Service
public class PautaService {

	private final PautaRepository pautaRepository;

	@Autowired
	public PautaService(PautaRepository pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	public List<Pauta> getPautas() {
		try {
			return pautaRepository.findAll();
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}

	public void addNewPauta(Pauta pauta) {
		Optional<Pauta> pautaOptional = pautaRepository.findPautaByDescricao(pauta.getDescricao());

		if (pautaOptional.isPresent()) {
			throw new IllegalStateException("Pauta inválida - Pauta já aberta!");
		}
		pautaRepository.save(pauta);
	}

	public Optional<Pauta> getPauta(Long id) {
		try {
			return pautaRepository.findById(id);
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}
}
