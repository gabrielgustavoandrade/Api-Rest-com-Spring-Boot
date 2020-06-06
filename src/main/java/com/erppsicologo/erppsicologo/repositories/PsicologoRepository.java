package com.erppsicologo.erppsicologo.repositories;

import com.erppsicologo.erppsicologo.entities.Psicologo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {

	Psicologo findByEmail(String email);

	@Query(value = "SELECT * FROM psicologo where email = ?1 ", nativeQuery = true)
    Psicologo findLogged(String email);

	void deleteByEmail(String email);
    
}