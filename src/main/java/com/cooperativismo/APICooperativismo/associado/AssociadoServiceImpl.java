package com.cooperativismo.APICooperativismo.associado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cooperativismo.APICooperativismo.tratamentoErros.ApiException;

@Service
public class AssociadoServiceImpl implements AssociadoService {

	private final AssociadoRepository associadoRepository;

	@Autowired
	public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}

	public List<Associado> getAssociados() {		
		try {
			return associadoRepository.findAll();
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}

	public void addNewAssociado(Associado associado) {
		Optional<Associado> associadoOptional = associadoRepository.findAssociadoByCpf(associado.getCpf());

		if (associadoOptional.isPresent()) {
			throw new IllegalStateException("Associado inválido - CPF de associado já cadastrado!");
		}
		associadoRepository.save(associado);
	}
}
