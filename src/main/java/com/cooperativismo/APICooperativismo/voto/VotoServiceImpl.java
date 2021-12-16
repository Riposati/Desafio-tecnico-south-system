package com.cooperativismo.APICooperativismo.voto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cooperativismo.APICooperativismo.associado.AssociadoRepository;
import com.cooperativismo.APICooperativismo.pauta.PautaRepository;
import com.cooperativismo.APICooperativismo.tratamentoErros.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class VotoServiceImpl implements VotoService {

	private final VotoRepository votoRepository;
	private final AssociadoRepository associadoRepository;
	private final PautaRepository pautaRepository;

	@Autowired
	public VotoServiceImpl(VotoRepository votoRepository, AssociadoRepository associadosRepository,
			PautaRepository pautaRepository) {
		this.votoRepository = votoRepository;
		this.associadoRepository = associadosRepository;
		this.pautaRepository = pautaRepository;
	}

	public List<Voto> getVotos() {
		try {
			return votoRepository.findAll();
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}

	public void addNewVoto(Voto voto) {

		if (voto.getAssociado().getCpf() != null && voto.getPauta().getIdPauta() != null) {

			if (!associadoRepository.findById(voto.getAssociado().getIdAssociado()).isEmpty()
					&& !pautaRepository.findById(voto.getPauta().getIdPauta()).isEmpty()) {

				boolean podeVotar = validaSePodeVotarPorCpf(voto);
				invokeAddNewVote(voto, podeVotar);
			}

			else {
				throw new IllegalStateException("Voto inválido - Associado ou pauta não encontrados!");
			}

		} else {
			throw new IllegalStateException("Voto inválido - Associado ou pauta inválido(s)!");
		}
	}

	private void invokeAddNewVote(Voto voto, boolean podeVotar) {
		if (podeVotar) {

			if (votoRepository.findIfAlreadyVoted(voto.getAssociado().getIdAssociado(),
					voto.getPauta().getIdPauta()) == 0) {
				votoRepository.save(voto);
			} else {
				throw new IllegalStateException(
						"Voto inválido - Voto não pode ser repetido, mesmo usuário votando na mesma pauta!");
			}

		} else {
			throw new IllegalStateException("CPF inválido não é permitido esse voto!");
		}
	}

	private boolean validaSePodeVotarPorCpf(Voto voto) {

		String url = "https://user-info.herokuapp.com/users/" + voto.getAssociado().getCpf();
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);

		try {
			Map<String, Object> jsonToMap = new ObjectMapper().readValue(result, Map.class);

			if (jsonToMap.containsValue("ABLE_TO_VOTE")) {
				return true;
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Long getVotosPauta(Long id) {

		try {
			return votoRepository.findAllEnabled(id);
		} catch (ApiException apiException) {
			throw apiException;
		} catch (Exception exception) {
			throw new ApiException(exception.getMessage(), exception);
		}
	}
}
