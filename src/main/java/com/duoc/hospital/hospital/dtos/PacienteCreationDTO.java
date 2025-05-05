package com.duoc.hospital.hospital.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString
public class PacienteCreationDTO {
    @NotBlank(message = "El campo rut no puede ser vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El fomato del dni debe ser XXXXXXXX-X")
    private String run;

    @NotBlank(message = "El campo nombres no puede ser vacio")
    private String nombres;

    @NotBlank(message = "El campo apellidos no puede ser vacio")
    private String apellidos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo fecha de nacimiento no puede ser vacio")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El campo correo no puede ser vacio")
    @Email(message = "El correo debe tener un formato de correo")
    private String correo;

    @NotNull(message = "El campo tipo de usuario id no puede ser vacio")
    private Long tipoUsuarioId;

    @NotBlank(message = "El campo de datos personales 1 no puede ser vacio")
    private String datosPersonales1;

    @NotBlank(message = "El campo de datos personales 2 no puede ser vacio")
    private String datosPersonales2;

    @NotBlank(message = "El campo de datos personales 3 no puede ser vacio")
    private String datosPersonales3;

    @NotBlank(message = "El campo de datos personales 4 no puede ser vacio")
    private String datosPersonales4;

    @NotBlank(message = "El campo de datos personales 5 no puede ser vacio")
    private String datosPersonales5;


}
