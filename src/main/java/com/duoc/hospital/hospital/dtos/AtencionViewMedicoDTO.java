package com.duoc.hospital.hospital.dtos;

import com.duoc.hospital.hospital.models.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AtencionViewMedicoDTO {
    private Long atencionId;
    private LocalDateTime horaAtencion;
    private Integer costo;
    private String comentario;
    private String runPaciente;
    private String nombrePaciente;

    public AtencionViewMedicoDTO(
            Long atencionId,
            LocalDateTime horaAtencion,
            String comentario,
            Integer costo,
            Paciente paciente
    ){
        this.atencionId = atencionId;
        this.horaAtencion = horaAtencion;
        this.comentario = comentario;
        this.costo = costo;
        this.nombrePaciente = paciente.getNombres() + paciente.getApellidos();
        this.runPaciente = paciente.getRun();
    }
}
