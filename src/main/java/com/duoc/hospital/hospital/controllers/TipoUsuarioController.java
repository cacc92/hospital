package com.duoc.hospital.hospital.controllers;

import com.duoc.hospital.hospital.models.TipoUsuario;
import com.duoc.hospital.hospital.services.TipoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipos-usuarios")
@Validated
public class TipoUsuarioController {
    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tipoUsuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tipoUsuarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> save(@Valid @RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tipoUsuarioService.save(tipoUsuario));
    }


}
