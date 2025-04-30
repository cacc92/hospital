package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.dtos.AtencionViewPacienteDTO;
import com.duoc.hospital.hospital.dtos.PacienteCreationDTO;
import com.duoc.hospital.hospital.dtos.PacienteDetalleDTO;
import com.duoc.hospital.hospital.exceptions.FichaPacienteException;
import com.duoc.hospital.hospital.exceptions.PacienteException;
import com.duoc.hospital.hospital.exceptions.TipoUsuarioException;
import com.duoc.hospital.hospital.models.Paciente;
import com.duoc.hospital.hospital.models.TipoUsuario;
import com.duoc.hospital.hospital.repositories.PacienteRepository;
import com.duoc.hospital.hospital.repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findById(Long id) {
        // Se genera metodo que permite realizar la busqueda de un usuario con el id, en caso de no encontrar un resul
        // Se lanza una excepcion que tiene que ver con paciente.
        return pacienteRepository.findById(id).orElseThrow(
                () -> new PacienteException("Paciente con id " + id + " no encontrado")
        );
    }

    @Transactional
    @Override
    public Paciente save(PacienteCreationDTO paciente) {
        // Se realiza la validacion que el usuario con el RUT enviado no exista previamente en nuestra base de datos
        if (pacienteRepository.findByRun(paciente.getRun()).isPresent()) {
            throw new PacienteException("Paciente con Run "+paciente.getRun()+" ya existente");
        }
        // Se realiza la validacion que el usuario con el Correo enviado no exista previamente en nuestra base de datos
        if (pacienteRepository.findByCorreo(paciente.getCorreo()).isPresent()) {
            throw new PacienteException("Paciente con Correo "+paciente.getCorreo()+" ya existente");
        }

        TipoUsuario tipoUsuario = tipoUsuarioRepository.findById(paciente.getTipoUsuarioId()).orElseThrow(
                () -> new TipoUsuarioException("TipoUsuario con id "+paciente.getTipoUsuarioId()+" no encontrado")
        );

        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setRun(paciente.getRun());
        pacienteEntity.setCorreo(paciente.getCorreo());
        pacienteEntity.setNombres(paciente.getNombres());
        pacienteEntity.setApellidos(paciente.getApellidos());
        pacienteEntity.setFechaNacimiento(paciente.getFechaNacimiento());
        pacienteEntity.setTipoUsuario(tipoUsuario);
        return pacienteRepository.save(pacienteEntity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente updateById(Long id, Paciente paciente) {
        // Metosdo lamda que nos permite generar la actualizacion de un elemento a traves del metodo MAP en caso de
        // que se encuentre un valor, en caso contrario se genera la excpecion del caso
        return pacienteRepository.findById(id).map(p->{
            p.setNombres(paciente.getNombres());
            p.setApellidos(paciente.getApellidos());
            p.setCorreo(paciente.getCorreo());
            p.setFechaNacimiento(paciente.getFechaNacimiento());
            p.setRun(paciente.getRun());
            return pacienteRepository.save(p);
        }).orElseThrow(
                () -> new PacienteException("Paciente con id " + id + " no encontrado")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findByCorreo(String correo) {
        return pacienteRepository.findByCorreo(correo).orElseThrow(
                () -> new PacienteException("Paciente con Correo "+correo+" no encontrado")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findByRun(String run) {
        return pacienteRepository.findByRun(run).orElseThrow(
                () -> new PacienteException("Paciente con Run "+run+" no encontrado")
        );
    }

    @Override
    public List<AtencionViewPacienteDTO> findByAtencionesByPacienteId(Long idPaciente) {
        Paciente paciente = pacienteRepository.findById(idPaciente).orElseThrow(
                () -> new PacienteException("Paciente con id "+idPaciente+" no encontrado")
        );
        return paciente.getAtenciones().stream().map(atencion -> {
            return new AtencionViewPacienteDTO(
                    atencion.getAtencionId(),
                    atencion.getHoraAtencion(),
                    atencion.getComentario(),
                    atencion.getCosto(),
                    atencion.getMedico()
            );
        }).collect(Collectors.toList());
    }

    @Override
    public PacienteDetalleDTO findByPacienteId(Long idPaciente) {
        // Como la ficha paciente tiene el mismo ID que ficha paciente puedo asumir que la busqqueda se encuentra
        FichaPaciente fichaPaciente = fichaPacienteRepository.findById(idPaciente).orElseThrow(
                () -> new FichaPacienteException("Ficha paciente con id "+idPaciente+" no encontrada")
        );
        return pacienteRepository.findById(idPaciente).map(paciente -> {
            PacienteDetalleDTO dto = new PacienteDetalleDTO();
            dto.setDatosPersonales1(fichaPaciente.getDatosPersonales1());
            dto.setDatosPersonales2(fichaPaciente.getDatosPersonales2());
            dto.setDatosPersonales3(fichaPaciente.getDatosPersonales3());
            dto.setDatosPersonales4(fichaPaciente.getDatosPersonales4());
            dto.setDatosPersonales5(fichaPaciente.getDatosPersonales5());
            dto.setNombreCompleto(paciente.getNombres()+" "+paciente.getApellidos());
            dto.setFechaNacimiento(paciente.getFechaNacimiento());
            dto.setRun(paciente.getRun());
            dto.setCorreo(paciente.getCorreo());
            dto.setAtencionViewPacienteDTOList(paciente.getAtenciones().stream().map(atencion -> {
                return new AtencionViewPacienteDTO(
                        atencion.getAtencionId(),
                        atencion.getHoraAtencion(),
                        atencion.getComentario(),
                        atencion.getCosto(),
                        atencion.getMedico()
                );
            }).collect(Collectors.toList()));
            return dto;
        }).orElseThrow(
                () -> new PacienteException("Paciente con id "+idPaciente+" no encontrado")
        );
    }


}
