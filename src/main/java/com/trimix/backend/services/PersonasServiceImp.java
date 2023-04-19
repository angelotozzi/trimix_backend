package com.trimix.backend.services;

import com.trimix.backend.models.entities.Persona;
import com.trimix.backend.repositories.PersonasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonasServiceImp implements PersonasService {

    @Autowired
    private PersonasRepository repository;


    @Override
    @Transactional(readOnly = true)
    public List<Persona> obtenerTodos() {
        return (List<Persona>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> obtenerPorId(Long devId) {
        return repository.findById(devId);
    }

    @Override
    @Transactional
    public Persona guardar(Persona persona) {
        return repository.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Long devId) {
        repository.deleteById(devId);
    }

    @Override
    public List<Persona> obtenerPorNombreYTipoDoc(String perNombre, String perTipoDocumento) {
        return repository.obtenerPorNombreYTipoDoc(perNombre, perTipoDocumento);
    }


}
