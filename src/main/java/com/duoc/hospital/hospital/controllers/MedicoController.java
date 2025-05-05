package com.duoc.hospital.hospital.controllers;

import com.duoc.hospital.hospital.dtos.AtencionViewMedicoDTO;
import com.duoc.hospital.hospital.models.Medico;
import com.duoc.hospital.hospital.services.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/medicos")
@Validated
public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.findById(id));
    }

    @GetMapping("/rut/{run}")
    public ResponseEntity<Medico> findByRun(@PathVariable String run) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.findByRun(run));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Medico>> deleteById(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PostMapping
    public ResponseEntity<Medico> save(@Valid @RequestBody Medico medico){
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicoService.save(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateById(@PathVariable Long id, @Valid @RequestBody Medico medico){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicoService.updateById(id, medico));
    }

    @GetMapping("/{id}/atenciones")
    public ResponseEntity<List<AtencionViewMedicoDTO>> findAtencionesByMedicoId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(medicoService.findAtencionByMedicoId(id));
    }

}
