package com.duoc.hospital.hospital.repositories;

import com.duoc.hospital.hospital.models.FichaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaPacienteRepository  extends JpaRepository<FichaPaciente, Long> {

}
