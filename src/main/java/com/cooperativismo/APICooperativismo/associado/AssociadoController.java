package com.cooperativismo.APICooperativismo.associado;

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
@Api(value = "associado")
@RequestMapping("cooperativismo/associado")
public class AssociadoController {

	private final AssociadoService associadoService;

	@Autowired
	public AssociadoController(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todos os associados")
	public List<Associado> getAssociados() {
		return associadoService.getAssociados();
	}

	@PostMapping
	@ApiOperation(value = "Salva um associado")
	public void registerNewAssociado(@RequestBody Associado associado) {
		associadoService.addNewAssociado(associado);
	}
}
