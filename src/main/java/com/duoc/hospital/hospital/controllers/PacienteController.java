package com.duoc.hospital.hospital.controllers;

import com.duoc.hospital.hospital.dtos.AtencionViewDTO;
import com.duoc.hospital.hospital.dtos.AtencionViewPacienteDTO;
import com.duoc.hospital.hospital.dtos.PacienteCreationDTO;
import com.duoc.hospital.hospital.dtos.PacienteDetalleDTO;
import com.duoc.hospital.hospital.models.Paciente;
import com.duoc.hospital.hospital.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RestController -> De esta manera definimos un controlador del tipo Rest si solamente ponemos Controller estaremos
 * definiendo un controlador para la arquitectura MVC
 * @RequestMapping -> Define la ruta principal de entrada del controller
 * @Validated -> Mencionamos que en la clase controller se haran validaciones del body con repescto a la clase
 * que ingresa, junto a su logica de negocio.
 */
@RestController
@RequestMapping("/api/v1/pacientes")
@Validated
public class PacienteController {

    // Se importa la interfaz del service dado que el controller no necesita saber como se encuentra implementado el
    // PacienteServiceImpl
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.findById(id));
    }

    @GetMapping("/email/{correo}")
    public ResponseEntity<Paciente> getPacienteByCorreo(@PathVariable String correo) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.findByCorreo(correo));
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@Valid @RequestBody PacienteCreationDTO paciente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pacienteService.save(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@Valid @RequestBody Paciente paciente, @PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pacienteService.updateById(id,paciente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}/atenciones")
    public ResponseEntity<List<AtencionViewPacienteDTO>> getPacienteAtenciones(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.findByAtencionesByPacienteId(id));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<PacienteDetalleDTO> getPacienteDetalleById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pacienteService.findByPacienteId(id));
    }


}
