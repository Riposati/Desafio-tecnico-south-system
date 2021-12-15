package com.cooperativismo.APICooperativismo.voto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "voto")
@RequestMapping("cooperativismo/voto")
public class VotoController {

	private final VotoServiceImpl votoService;

	@Autowired
	public VotoController(VotoServiceImpl votoService) {
		this.votoService = votoService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todos os votos")
	public List<Voto> getVotos() {
		return votoService.getVotos();
	}
	
	@GetMapping("/pauta/{idPauta}")
	@ApiOperation(value = "Retorna os votos de uma pauta a partir do ID da pauta")
	public Long getVotosPauta(@PathVariable("idPauta") Long id) {
		return votoService.getVotosPauta(id);
	}
	
	@PostMapping()
	@ApiOperation(value = "Salva um voto")
	public void registerNewPauta(@RequestBody Voto voto) {
		votoService.addNewVoto(voto);
	}
}
