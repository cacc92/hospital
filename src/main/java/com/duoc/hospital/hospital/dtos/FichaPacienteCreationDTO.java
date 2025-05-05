package com.duoc.hospital.hospital.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter @Setter @NoArgsConstructor @ToString
public class FichaPacienteCreationDTO {

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales1;

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales2;

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales3;

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales4;

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales5;

    @NotNull(message = "El campo de paciente id no puede ser vacio")
    private Long pacienteId;
}
