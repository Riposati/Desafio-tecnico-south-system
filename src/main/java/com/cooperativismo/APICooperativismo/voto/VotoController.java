package com.cooperativismo.APICooperativismo.voto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

	private final VotoService votoService;

	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todos os votos")
	public List<Voto> getVotos() {
		return votoService.getVotos();
	}
	
	@PostMapping()
	@ApiOperation(value = "Salva um voto")
	public void registerNewPauta(@RequestBody Voto voto) {
		votoService.addNewVoto(voto);
	}
}
