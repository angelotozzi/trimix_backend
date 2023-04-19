package com.trimix.backend.controllers;

import com.trimix.backend.Response.ResponseDefault;
import com.trimix.backend.exception.ResultNotFound;
import com.trimix.backend.models.entities.Persona;
import com.trimix.backend.services.PersonasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(value = "/personas")
public class PersonasController {

    @Autowired
    private PersonasService service;

    @GetMapping(value = "/obtenerTodos")
    public ResponseEntity<List<Persona>> obtenerTodos(){

            List<Persona> personas = service.obtenerTodos();

            if(CollectionUtils.isEmpty(personas)){
                ResponseDefault responseDefault = ResponseDefault
                        .builder()
                        .action("Se consulto al servicio obtenerTodos()")
                        .message("NO SE OBTUVIERON RESULTADOS")
                        .build();
                throw new ResultNotFound(responseDefault);
            }

            return ResponseEntity.ok(personas);

    }

    @GetMapping(value = "/obtenerPorId/{perId}")
    public ResponseEntity<Persona> obtenerPorId(@RequestParam Long perId){

        Persona persona = service.obtenerPorId(perId).orElse(null);

        if(Objects.isNull(persona)){
            ResponseDefault responseDefault = ResponseDefault
                    .builder()
                    .action("Se consulto al servicio obtenerPorId()")
                    .message("NO SE OBTUVIERON RESULTADOS")
                    .build();
            throw new ResultNotFound(responseDefault);
        }

        return ResponseEntity.ok(persona);
    }


    @GetMapping(value = "/obtenerPorNombreYTipoDoc")
    public ResponseEntity<List<Persona>> obtenerPorNombreYTipoDoc(@RequestParam String perNombre, @RequestParam String perTipoDocumento){

        List<Persona> personas = service.obtenerPorNombreYTipoDoc(perNombre, perTipoDocumento);

        if(CollectionUtils.isEmpty(personas)){
            ResponseDefault responseDefault = ResponseDefault
                    .builder()
                    .action("Se consulto al servicio obtenerPorNombreYTipoDoc()")
                    .message("NO SE OBTUVIERON RESULTADOS")
                    .build();
            throw new ResultNotFound(responseDefault);
        }

        return ResponseEntity.ok(personas);
    }


    @PostMapping(value = "/crear")
    public ResponseEntity<Persona> crear(@Valid @RequestBody Persona persona){

            Persona personaSaved = service.guardar(persona);

            if(Objects.isNull(personaSaved)){
                ResponseDefault responseDefault = ResponseDefault
                        .builder()
                        .action("Se consulto al servicio guardar()")
                        .message("NO SE LOGRO CREAR LA PERSONA")
                        .build();
                throw new ResultNotFound(responseDefault);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(personaSaved);

    }

    @PutMapping(value = "/editar")
    public ResponseEntity<Persona> editar(@RequestParam Long perId,@Valid @RequestBody Persona persona){

        Persona personaDb = service.obtenerPorId(perId).orElse(null);

        if(Objects.isNull(personaDb)){
            ResponseDefault responseDefault = ResponseDefault
                    .builder()
                    .action("Se consulto al servicio obtenerPorId()")
                    .message("NO SE LOGRO GUARDAR LA PERSONA")
                    .build();
            throw new ResultNotFound(responseDefault);
        }

        personaDb.setPerNombre(persona.getPerNombre());
        personaDb.setPerApellido(persona.getPerApellido());
        personaDb.setPerNumeroDocumento(persona.getPerNumeroDocumento());
        personaDb.setPerTipoDocumento(persona.getPerTipoDocumento());
        personaDb.setPerFechaNacimiento(persona.getPerFechaNacimiento());

        Persona personaSaved = service.guardar(personaDb);

        if(Objects.isNull(personaSaved)){
            ResponseDefault responseDefault = ResponseDefault
                    .builder()
                    .action("Se consulto al servicio guardar()")
                    .message("NO SE LOGRO GUARDAR LA PERSONA")
                    .build();
            throw new ResultNotFound(responseDefault);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(personaSaved);

    }

    @DeleteMapping(value = "/eliminar")
    public ResponseEntity<Long> eliminar(@RequestParam Long perId){

            Persona personaDb = service.obtenerPorId(perId).orElse(null);

            if(Objects.isNull(personaDb)){
                ResponseDefault responseDefault = ResponseDefault
                        .builder()
                        .action("Se consulto al servicio obtenerPorId()")
                        .message("NO SE LOGRO ELIMINAR LA PERSONA")
                        .build();
                throw new ResultNotFound(responseDefault);
            }

            service.eliminar(perId);

            return ResponseEntity.ok().body(perId);

    }



}
