package com.duoc.hospital.hospital.exceptions;

import com.duoc.hospital.hospital.models.FichaPaciente;

public class FichaPacienteException extends RuntimeException {
    public FichaPacienteException(String message) {
        super(message);
    }
}
