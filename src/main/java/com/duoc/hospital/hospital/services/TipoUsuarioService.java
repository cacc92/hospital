package com.duoc.hospital.hospital.services;

import com.duoc.hospital.hospital.models.TipoUsuario;

import java.util.List;

public interface TipoUsuarioService {

    List<TipoUsuario> findAll();
    TipoUsuario findById(Long id);
    TipoUsuario save(TipoUsuario tipoUsuario);
    Integer getTotalByTipoUsuario(Long idTipoUsuario);

}
