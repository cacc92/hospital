package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.dtos.FichaPacienteCreationDTO;
import com.duoc.hospital.hospital.exceptions.FichaPacienteException;
import com.duoc.hospital.hospital.exceptions.PacienteException;
import com.duoc.hospital.hospital.models.FichaPaciente;
import com.duoc.hospital.hospital.models.Paciente;
import com.duoc.hospital.hospital.repositories.FichaPacienteRepository;
import com.duoc.hospital.hospital.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FichaPacienteServiceImpl implements FichaPacienteService {
    @Autowired
    private FichaPacienteRepository fichaPacienteRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional(readOnly = true)
    @Override
    public List<FichaPaciente> findAll() {
        return fichaPacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public FichaPaciente findById(Long id) {
        return fichaPacienteRepository.findById(id).orElseThrow(
                () -> new FichaPacienteException("La ficha paciente con id " + id + " no existe")
        );
    }

    @Transactional
    @Override
    public FichaPaciente save(FichaPacienteCreationDTO fichaPaciente) {

        FichaPaciente fichaPacienteEntity = new FichaPaciente();
        fichaPacienteEntity.setDatosPersonales1(fichaPaciente.getDatosPersonales1());
        fichaPacienteEntity.setDatosPersonales2(fichaPaciente.getDatosPersonales2());
        fichaPacienteEntity.setDatosPersonales3(fichaPaciente.getDatosPersonales3());
        fichaPacienteEntity.setDatosPersonales4(fichaPaciente.getDatosPersonales4());
        fichaPacienteEntity.setDatosPersonales5(fichaPaciente.getDatosPersonales5());

        Paciente paciente = pacienteRepository.findById(fichaPaciente.getPacienteId()).orElseThrow(
                () -> new PacienteException("El paciente con id " + fichaPaciente.getPacienteId() + " no existe")
        );

        System.out.println(paciente.getRun());
        fichaPacienteEntity.setPaciente(paciente);

        return fichaPacienteRepository.save(fichaPacienteEntity);

    }
}
