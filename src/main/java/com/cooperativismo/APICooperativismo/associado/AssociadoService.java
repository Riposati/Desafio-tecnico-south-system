package com.cooperativismo.APICooperativismo.associado;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

	private final AssociadoRepository associadoRepository;

	@Autowired
	public AssociadoService(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}

	public List<Associado> getAssociados() {
		return associadoRepository.findAll();
	}

	public void addNewAssociado(Associado associado) {
		Optional<Associado> associadoOptional = associadoRepository.findAssociadoByCpf(associado.getCpf());

		if (associadoOptional.isPresent()) {
			throw new IllegalStateException("CPF de associado já cadastrado!");
		}
		associadoRepository.save(associado);
	}
}
