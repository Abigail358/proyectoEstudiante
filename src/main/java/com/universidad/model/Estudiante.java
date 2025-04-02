package com.universidad.model;

import java.util.Optional;

import com.universidad.dto.EstudianteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Estudiante extends Persona{
    private String nroInscripcion;

}
