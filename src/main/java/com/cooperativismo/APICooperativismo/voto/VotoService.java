package com.cooperativismo.APICooperativismo.voto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cooperativismo.APICooperativismo.associado.AssociadoRepository;
import com.cooperativismo.APICooperativismo.pauta.PautaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VotoService {

	private final VotoRepository votoRepository;
	private final AssociadoRepository associadoRepository;
	private final PautaRepository pautaRepository;

	@Autowired
	public VotoService(VotoRepository votoRepository, AssociadoRepository associadosRepository,
			PautaRepository pautaRepository) {
		this.votoRepository = votoRepository;
		this.associadoRepository = associadosRepository;
		this.pautaRepository = pautaRepository;
	}

	public List<Voto> getVotos() {
		return votoRepository.findAll();
	}

	public void addNewVoto(Voto voto) {

//		System.out.println(voto);

		if (voto.getAssociado().getCpf() != null && voto.getPauta().getIdPauta() != null) {

			if (!associadoRepository.findById(voto.getAssociado().getIdAssociado()).isEmpty()
					&& !pautaRepository.findById(voto.getPauta().getIdPauta()).isEmpty()) {

				boolean podeVotar = validaSePodeVotarPorCpf(voto);

				if (podeVotar) {

					if (votoRepository.findVotoByAssociado(voto.getAssociado()).isEmpty()) {
						votoRepository.save(voto);
					} else
						throw new IllegalStateException("Voto não pode ser repetido!");

				} else {
					throw new IllegalStateException("CPF inválido não é permitido esse voto!");
				}
			}

			else {
				throw new IllegalStateException("Voto inválido!");
			}

		} else {
			throw new IllegalStateException("Voto inválido!");
		}
	}

	@SuppressWarnings("unchecked")
	private boolean validaSePodeVotarPorCpf(Voto voto) {

		String url = "https://user-info.herokuapp.com/users/" + voto.getAssociado().getCpf();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);

		try {
			Map<String, Object> jsonToHashMap = new ObjectMapper().readValue(result, HashMap.class);

			if (jsonToHashMap.containsValue("ABLE_TO_VOTE")) {
				return true;
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public Long getVotosPauta(Long id) {		
		return votoRepository.findAllEnabled(id);
	}
}
