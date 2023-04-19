package com.trimix.backend.services;

import com.trimix.backend.models.entities.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonasService {
    public List<Persona> obtenerTodos();
    public Optional<Persona> obtenerPorId(Long devId);
    public Persona guardar(Persona persona);
    public void eliminar(Long devId);
    public List<Persona> obtenerPorNombreYTipoDoc(String perNombre, String perTipoDocumento);

}
