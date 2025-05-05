package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.dtos.FichaPacienteCreationDTO;
import com.duoc.hospital.hospital.models.FichaPaciente;

import java.util.List;

public interface FichaPacienteService {

    List<FichaPaciente> findAll();
    FichaPaciente findById(Long id);
    FichaPaciente save(FichaPacienteCreationDTO fichaPaciente);

}
