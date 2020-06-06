package com.erppsicologo.erppsicologo.repositories;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query(value = "SELECT pa.* FROM psicologo P INNER JOIN paciente pa on pa.psicologo_id = p.id where p.email = ?1 LIMIT ?2 offset ?3", nativeQuery = true)
    List<Paciente> findAllByPsicolgoEmail(String email, Integer limit, Integer pagina);

	Paciente findByCpf(String cpf);

	Paciente findByEmail(String email);

}