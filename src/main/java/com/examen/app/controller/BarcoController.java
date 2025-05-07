package com.examen.app.controller;

import com.examen.app.entity.Barco;
import com.examen.app.entity.Contenedor;
import com.examen.app.service.BarcoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barcos")
public class BarcoController {

    @Autowired
    private BarcoService barcoService;

    @GetMapping
    public List<Barco> getAllBarcos() {
        return barcoService.getAllBarcos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBarcoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(barcoService.getBarcoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Barco no encontrado: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> saveBarco(@RequestBody Barco barco) {
        try {
            Barco nuevoBarco = barcoService.saveBarco(barco);
            return ResponseEntity.ok(nuevoBarco);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el barco: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBarco(@PathVariable Long id) {
        try {
            barcoService.deleteBarco(id);
            return ResponseEntity.ok("Barco eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al eliminar el barco: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBarco(@PathVariable Long id, @RequestBody Barco barco) {
        try {
            return ResponseEntity.ok(barcoService.updateBarco(id, barco));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Error al actualizar el barco: " + e.getMessage());
        }
    }

    @GetMapping("/contenedores/{id}")
    public String verContenedores(@PathVariable Long id, Model model) {
        Barco barco = barcoService.getBarcoById(id);
        List<Contenedor> contenedores = barco.getContenedores();

        model.addAttribute("barco", barco);
        model.addAttribute("contenedores", contenedores);

        return "contenedores"; // Nombre de la plantilla HTML (contenedores.html)
    }

}