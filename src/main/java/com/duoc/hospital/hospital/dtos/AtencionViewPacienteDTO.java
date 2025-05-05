package com.duoc.hospital.hospital.dtos;

import com.duoc.hospital.hospital.models.Medico;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AtencionViewPacienteDTO {
    private Long atencionId;
    private LocalDateTime horaAtencion;
    private Integer costo;
    private String comentario;
    private String runMedico;
    private String nombreMedico;


    public AtencionViewPacienteDTO(
            Long atencionId,
            LocalDateTime horaAtencion,
            String comentario,
            Integer costo,
            Medico medico
    ){
        this.atencionId = atencionId;
        this.horaAtencion = horaAtencion;
        this.comentario = comentario;
        this.costo = costo;
        this.runMedico = medico.getRun();
        this.nombreMedico = medico.getNombreCompleto();
    }
}
