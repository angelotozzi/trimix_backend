package com.trimix.backend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Long perId;

    @NotBlank
    @Column(name = "per_nombre")
    private String perNombre;

    @NotBlank
    @Column(name = "per_apellido")
    private String perApellido;

    @NotNull
    @Column(name = "per_numero_documento")
    private Long perNumeroDocumento;

    @NotBlank
    @Column(name = "per_tipo_documento")
    private String perTipoDocumento;

    @NotNull
    @Column(name = "per_fecha_nacimiento")
    private LocalDate perFechaNacimiento;


}
