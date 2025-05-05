package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.exceptions.TipoUsuarioException;
import com.duoc.hospital.hospital.models.Atencion;
import com.duoc.hospital.hospital.models.Paciente;
import com.duoc.hospital.hospital.models.TipoUsuario;
import com.duoc.hospital.hospital.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<TipoUsuario> findAll() {
        return tipoUsuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public TipoUsuario findById(Long id) {
        return tipoUsuarioRepository.findById(id).orElseThrow(
                () -> new TipoUsuarioException("Tipo usuario id "+id+" no encontrado")
        );
    }

    @Transactional
    @Override
    public TipoUsuario save(TipoUsuario tipoUsuario) {
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    @Override
    public Integer getTotalByTipoUsuario(Long idTipoUsuario) {
        TipoUsuario tipoUsuario  = this.findById(idTipoUsuario);
        List<Paciente> pacientes = tipoUsuario.getPacientes();
        Integer total = 0;
        for (Paciente paciente : pacientes) {
            List<Atencion>  atencions = paciente.getAtenciones();
            for (Atencion atencion : atencions) {
                total = total + atencion.getCosto();
            }
        }
        return total;
    }
}
