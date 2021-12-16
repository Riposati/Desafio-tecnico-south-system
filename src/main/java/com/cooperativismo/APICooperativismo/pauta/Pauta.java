package com.cooperativismo.APICooperativismo.pauta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Pauta {

	@Id
	@SequenceGenerator(name = "pauta_sequence", sequenceName = "pauta_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pauta_sequence")

	private Long idPauta;
	private String descricao;
	
	public Pauta() {
	}
	
	public Pauta(String descricao) {
		this.descricao = descricao;
	}
}
