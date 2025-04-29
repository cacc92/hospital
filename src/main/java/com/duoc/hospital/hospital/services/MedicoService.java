package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.dtos.AtencionViewMedicoDTO;
import com.duoc.hospital.hospital.models.Medico;

import java.util.List;

public interface MedicoService {

    List<Medico> findAll();
    Medico findById(Long id);
    Medico findByRun(String run);
    Medico save(Medico medico);
    void deleteById(Long id);
    Medico updateById(Long id, Medico medico);
    List<AtencionViewMedicoDTO> findAtencionesById(Long id);

}
