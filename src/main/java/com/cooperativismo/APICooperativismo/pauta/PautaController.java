package com.cooperativismo.APICooperativismo.pauta;

import java.util.List;
import java.util.Optional;

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
@Api(value = "pauta")
@RequestMapping("cooperativismo/pauta")
public class PautaController {

	private final PautaServiceImpl pautaService;

	@Autowired
	public PautaController(PautaServiceImpl pautaService) {
		this.pautaService = pautaService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todas as pautas")
	public List<Pauta> getPautas() {
		return pautaService.getPautas();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna uma pauta")
	public Optional<Pauta> getPauta(@PathVariable("id") Long id) {
		return pautaService.getPauta(id);
	}

	@PostMapping
	@ApiOperation(value = "Salva uma pauta")
	public void registerNewPauta(@RequestBody Pauta pauta) {
		pautaService.addNewPauta(pauta);
	}
}
