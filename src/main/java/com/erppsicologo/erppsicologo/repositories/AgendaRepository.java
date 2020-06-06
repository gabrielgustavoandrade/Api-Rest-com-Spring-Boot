package com.erppsicologo.erppsicologo.repositories;

import java.util.List;

import com.erppsicologo.erppsicologo.entities.Agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer>{

    @Query(value = "select a.* from agenda a inner join psicologo psi on a.psicologo_id = psi.id where psi.email = ?1 ", nativeQuery = true)
    List<Agenda> findByPsicologo(String email);

    @Query(value = "select a.* from agenda a inner join psicologo psi on psi.id = a.psicologo_id where a.paciente_id = ?1 and psi.email = ?2 LIMIT ?3 offset ?4", nativeQuery = true)
    List<Agenda> buscarAgendaPacientesByPsicologo(Integer idPaciente, String emailPsicologo, Integer limit, Integer pagina);
    
}