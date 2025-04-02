package com.universidad.service;

import java.util.List;
import java.util.Optional;

import com.universidad.dto.EstudianteDTO;

public interface IEstudianteService { 
    //LEER ESTUDIANTE
    List<EstudianteDTO> obtenerTodosLosEstudiantes();

    //ACTUALIZAR ESTUDIANTE
    Optional<EstudianteDTO> obtenerEstudiantePorId(Long id);
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);
    
    //CREAR ESTUDIANTE
    EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO);
 
    //ELIMINAR ESTUDIANTE
    void eliminarEstudiantePorId(Long id); 
}