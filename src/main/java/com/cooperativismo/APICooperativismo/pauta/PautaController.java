package com.cooperativismo.APICooperativismo.pauta;

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
@Api(value = "pauta")
@RequestMapping("cooperativismo/pauta")
public class PautaController {

	private final PautaService pautaService;

	@Autowired
	public PautaController(PautaService pautaService) {
		this.pautaService = pautaService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todas as pautas")
	public List<Pauta> getPautas() {
		return pautaService.getPautas();
	}

	@PostMapping
	@ApiOperation(value = "Salva uma pauta")
	public void registerNewPauta(@RequestBody Pauta pauta) {
		pautaService.addNewPauta(pauta);
	}
}
