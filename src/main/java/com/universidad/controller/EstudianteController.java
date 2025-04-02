package com.universidad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.IEstudianteService;


@RestController 
@RequestMapping("/api") 

public class EstudianteController { 
    private final IEstudianteService estudianteService; 

    @Autowired 
    public EstudianteController(IEstudianteService estudianteService) { 
        this.estudianteService = estudianteService; 
    }

    //LEER ESTUDIANTE
    @GetMapping("/estudiantes") 
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { //Metodo para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); //Llama al servicio para obtener a todos los estudiantes
        return ResponseEntity.ok(estudiantes); //Retorna una respuesta HTTP 200 OK con la lista de estudiantes
    }
    
    //ACTUALIZAR ESTUDIANTE
    @PutMapping("/estudiantes/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) { //Llama al servicio para actualizar el estudiante con el ID proporcionado
        EstudianteDTO actualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        return ResponseEntity.ok(actualizado); // Retorna el estudiante actualizado con código 200 OK
    }

    //CREAR ESTUDIANTE
    @PostMapping("/estudiantes")
    public ResponseEntity<EstudianteDTO> registrarEstudiante(@RequestBody EstudianteDTO estudianteDTO) {  // Llama al servicio para registrar un nuevo estudiante
        EstudianteDTO nuevoEstudiante = estudianteService.registrarEstudiante(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);  // Llama al servicio para registrar un nuevo estudiante
    }

    //ELIMINAR ESTUDIANTE
    @DeleteMapping("/estudiantes/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {  // Llama al servicio para eliminar el estudiante por su ID
        estudianteService.eliminarEstudiantePorId(id);
        return ResponseEntity.noContent().build();  // Retorna 204 No Content si la eliminación fue exitosa 
    }
}

