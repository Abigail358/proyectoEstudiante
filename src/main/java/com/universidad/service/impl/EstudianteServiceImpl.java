package com.universidad.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;

import jakarta.annotation.PostConstruct;

@Service 
public class EstudianteServiceImpl implements IEstudianteService { 

    private final EstudianteRepository estudianteRepository; 

    @Autowired 
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) { 
        this.estudianteRepository = estudianteRepository; 
    }
    
    @PostConstruct 
    public void init() { 
        estudianteRepository.init(); 
    }

    @Override 
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() { 
        List<Estudiante> estudiantes = estudianteRepository.findAll(); 
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>(); 
        for (Estudiante estudiante : estudiantes) { 
            estudiantesDTO.add(convertirAToDTO(estudiante)); 
        }
        return estudiantesDTO; 
    }
    
    //ACTUALIZAR ESTUDIANTE
    //Obtiene un estudiante por su ID
    @Override
    public Optional<EstudianteDTO> obtenerEstudiantePorId(Long id) {
        return Optional.ofNullable(estudianteRepository.findById(id)).map(this::convertirAToDTO);
    }

    //Actualiza la informacion de un estudiante
    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteRepository.findById(id);
        if (estudiante != null) { // Verificamos si el estudiante existe
            estudiante.setNombre(estudianteDTO.getNombre());
            estudiante.setApellido(estudianteDTO.getApellido());
            estudiante.setEmail(estudianteDTO.getEmail());
            estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
            estudiante.setNroInscripcion(estudianteDTO.getNroIncripcion());

            estudianteRepository.save(estudiante); // Guardamos los cambios
            return convertirAToDTO(estudiante); // Convierte el estudiante actualizado a DTO y lo retorna
        } else {
            throw new RuntimeException("Estudiante con ID " + id + " no encontrado.");
        }
    }

    //CREAR ESTUDIANTE
    //Registra un nuevo estudiante
    @Override
    public EstudianteDTO registrarEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertirAEstudiante(estudianteDTO); // Convierte el DTO a una entidad Estudiante
        estudiante = estudianteRepository.save(estudiante); // Guarda el estudiante en el repositorio
        return convertirAToDTO(estudiante);// Convierte el estudiante guardado a DTO y lo retorna
    }
 
    //ELIMINAR ESTUDIANTE
    //Elimina un estudiante por su ID
    @Override
    public void eliminarEstudiantePorId(Long id) {
        estudianteRepository.deleteById(id); // Llama al repositorio para eliminar el estudiante
    }

    // Método auxiliar para convertir entidad a DTO
    private EstudianteDTO convertirAToDTO(Estudiante estudiante) { 
        return EstudianteDTO.builder() 
                .id(estudiante.getId()) 
                .nombre(estudiante.getNombre()) 
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail()) 
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .nroIncripcion(estudiante.getNroInscripcion()) 
                .build(); 
    }
    
    // Método auxiliar para convertir DTO a entidad
    private Estudiante convertirAEstudiante(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .nroInscripcion(estudianteDTO.getNroIncripcion())
                .build();
    }  
}