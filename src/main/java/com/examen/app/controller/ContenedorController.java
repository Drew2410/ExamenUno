package com.examen.app.controller;

import com.examen.app.entity.Contenedor;
import com.examen.app.service.ContenedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenedores")
public class ContenedorController {

    @Autowired
    private ContenedorService contenedorService;

    @GetMapping
    public List<Contenedor> getAllContenedores() {
        return contenedorService.getAllContenedores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContenedorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contenedorService.getContenedorById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Contenedor no encontrado: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveContenedor(@RequestBody Contenedor contenedor) {
        try {
            return ResponseEntity.ok(contenedorService.saveContenedor(contenedor));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al guardar el contenedor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteContenedor(@PathVariable Long id) {
        try {
            contenedorService.deleteContenedor(id);
            return ResponseEntity.ok("Contenedor eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al eliminar el contenedor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContenedor(@PathVariable Long id, @RequestBody Contenedor contenedor) {
        try {
            return ResponseEntity.ok(contenedorService.updateContenedor(id, contenedor));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al actualizar el contenedor: " + e.getMessage());
        }
    }

}