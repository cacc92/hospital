package com.duoc.hospital.hospital.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_usuario")
@Getter @Setter @ToString @NoArgsConstructor
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tipo_usuario_id")
    private Long tipoUsuarioId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El campo nombre no puede ser vacio")
    private String nombre;

    @OneToMany(mappedBy = "tipoUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("tipo-usuario")
    private List<Paciente> pacientes = new ArrayList<>();

}
