package com.duoc.hospital.hospital.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="medicos")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medico_id")
    private Long medicoId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El campo run no puede ser vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El fomato del dni debe ser XXXXXXXX-X")
    private String run;

    @Column(name="nombre_completo", nullable = false)
    @NotBlank(message = "El campo nombre completo no puede ser vacio")
    private String nombreCompleto;

    @Column(nullable = false)
    @NotNull(message = "El campo jefe de turno no puede ser vacio")
    private Boolean jefeTurno;

    @Embedded
    private Audit audit = new Audit();

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("medico-especialidad")
    private List<RegistroEspecialidad>  especialidades = new ArrayList<>();

    @OneToMany(mappedBy = "medico",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("medico-paciente")
    private List<Atencion> atenciones = new ArrayList<>();

}
