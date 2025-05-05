package com.duoc.hospital.hospital.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter @Getter @ToString @NoArgsConstructor
public class PacienteDetalleDTO {
    private String run;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private String correo;
    private String datosPersonales1;
    private String datosPersonales2;
    private String datosPersonales3;
    private String datosPersonales4;
    private String datosPersonales5;
    private List<AtencionViewPacienteDTO> atencionViewPacienteDTOList = new ArrayList<>();

}
