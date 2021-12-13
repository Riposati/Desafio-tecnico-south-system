package com.cooperativismo.APICooperativismo.associado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

	Optional<Associado> findAssociadoByCpf(String cpf);
}
