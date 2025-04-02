package com.universidad.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.universidad.model.Estudiante;

@Repository 
public class EstudianteRepository {
    private final Map<Long, Estudiante> estudiantes = new ConcurrentHashMap<>(); 
    private final AtomicLong idContador = new AtomicLong(1); 
    
    public Estudiante save(Estudiante estudiante) { 
        if (estudiante.getId() == null) { 
            estudiante.setId(idContador.getAndIncrement());
        }
        estudiantes.put(estudiante.getId(), estudiante); 
        return estudiante; 
    }
    
    //Metodo para obtener todos los estudiantes almacenados
    public List<Estudiante> findAll() { 
        return new ArrayList<>(estudiantes.values());
    }
    
    //Metodo para eleiminar un estudiante por su ID
    public void deleteById(Long id) { 
        estudiantes.remove(id); 
    }

    //Metodo para buscar un estudiante por su ID
    public Estudiante findById(Long id) { 
        return estudiantes.get(id); // Devuelve el estudiante si existe, o null si no se encuentra
    }
    
    // Método para inicializar algunos datos de ejemplo
    public void init() {
        Estudiante estudiante1 = Estudiante.builder() 
                .nombre("Juan") 
                .apellido("Pérez") 
                .email("juan.perez@example.com") 
                .fechaNacimiento(LocalDate.of(2000, 5, 15)) 
                .nroInscripcion("S001") 
                .build(); 
                
        Estudiante estudiante2 = Estudiante.builder() 
                .nombre("María") 
                .apellido("González") 
                .email("maria.gonzalez@example.com")
                .fechaNacimiento(LocalDate.of(2001, 8, 22)) 
                .nroInscripcion("S002") 
                .build(); 
                
        //Guardar los estudiantes en el repositorio
        save(estudiante1); 
        save(estudiante2); 
    }

}