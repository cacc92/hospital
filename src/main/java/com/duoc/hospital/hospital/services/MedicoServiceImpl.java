package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.dtos.AtencionViewMedicoDTO;
import com.duoc.hospital.hospital.exceptions.MedicoException;
import com.duoc.hospital.hospital.models.Medico;
import com.duoc.hospital.hospital.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico findById(Long id) {
        return medicoRepository.findById(id).orElseThrow(
                () -> new MedicoException("El medico con id " + id + " no existe")
        );
    }

    @Override
    public Medico findByRun(String run) {
        return medicoRepository.findByRun(run).orElseThrow(
                () -> new MedicoException("El medico con run " + run + " no existe")
        );
    }

    @Override
    public Medico save(Medico medico) {
        if (medicoRepository.findByRun(medico.getRun()).isPresent()) {
            throw new MedicoException("El medico con run " + medico.getRun() + " ya existe");
        }
        return medicoRepository.save(medico);
    }

    @Override
    public void deleteById(Long id) {
        medicoRepository.deleteById(id);
    }

    @Override
    public Medico updateById(Long id, Medico medico) {
        return medicoRepository.findById(id).map(m->{
            m.setRun(medico.getRun());
            m.setJefeTurno(medico.getJefeTurno());
            m.setNombreCompleto(medico.getNombreCompleto());
            return medicoRepository.save(m);
        }).orElseThrow(
                () -> new MedicoException("El medico con id " + id + " no existe")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<AtencionViewMedicoDTO> findAtencionesById(Long id) {
        Medico medico = this.findById(id);
        return medico.getAtenciones().stream().map(atencion -> {
            return new AtencionViewMedicoDTO(
                    atencion.getHoraAtencion(),
                    atencion.getCosto(),
                    atencion.getComentario(),
                    atencion.getPaciente().getRun(),
                    atencion.getPaciente().getNombres()+" "+atencion.getPaciente().getApellidos()
            );
        }).toList();
    }
}
