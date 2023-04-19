package com.trimix.backend.repositories;

import com.trimix.backend.models.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonasRepository extends CrudRepository<Persona, Long> {

    @Query("SELECT p FROM Persona p WHERE " +
            "(:perNombre IS NULL OR :perNombre = '' OR p.perNombre LIKE %:perNombre%) " +
            "AND (:perTipoDocumento IS NULL OR :perTipoDocumento = '' OR p.perTipoDocumento = :perTipoDocumento)")
    List<Persona> obtenerPorNombreYTipoDoc(@Param("perNombre") String perNombre, @Param("perTipoDocumento") String perTipoDocumento);

}
