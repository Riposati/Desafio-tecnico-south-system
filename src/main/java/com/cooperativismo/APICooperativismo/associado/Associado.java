package com.cooperativismo.APICooperativismo.associado;

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
public class Associado {

	@Id
	@SequenceGenerator(name = "associado_sequence", sequenceName = "associado_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="associado_sequence")

	private Long idAssociado;
	private String cpf;
}
