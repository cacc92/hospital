package com.duoc.hospital.hospital.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ficha_paciente")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class FichaPaciente {

    @Id
    @Column(name = "ficha_paciente_id")
    private Long fichaPacienteId;

    @Column(name = "datos_personales_1",nullable = false)
    private String datosPersonales1;
    @Column(name = "datos_personales_2",nullable = false)
    private String datosPersonales2;
    @Column(name = "datos_personales_3",nullable = false)
    private String datosPersonales3;
    @Column(name = "datos_personales_4",nullable = false)
    private String datosPersonales4;
    @Column(name = "datos_personales_5",nullable = false)
    private String datosPersonales5;

    @OneToOne
    @MapsId
    @JoinColumn(name="paciente_id",nullable = false)
    private Paciente paciente;

}
