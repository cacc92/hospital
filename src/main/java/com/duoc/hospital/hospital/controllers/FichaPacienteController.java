package com.duoc.hospital.hospital.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fichas-pacientes")
@Validated
public class FichaPacienteController {
    @Autowired
    private FichaPacienteService fichaPacienteService;

    @GetMapping
    public ResponseEntity<List<FichaPaciente>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fichaPacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaPaciente> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fichaPacienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FichaPaciente> save(@Valid @RequestBody FichaPacienteCreationDTO fichaPaciente) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fichaPacienteService.save(fichaPaciente));
    }
}
