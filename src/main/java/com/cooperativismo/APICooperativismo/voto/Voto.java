package com.cooperativismo.APICooperativismo.voto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cooperativismo.APICooperativismo.associado.Associado;
import com.cooperativismo.APICooperativismo.pauta.Pauta;

import lombok.Data;

@Data
@Entity
@Table(name = "voto")
public class Voto {

	@Id
	@SequenceGenerator(name = "voto_sequence", sequenceName = "voto_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="voto_sequence")

	private Long idVoto;
	private boolean voto;
	
	@JoinColumn(name = "idAssociado")
	@ManyToOne
	private Associado associado;
	
	@JoinColumn(name = "idPauta")
	@ManyToOne
	private Pauta pauta;
}
