package com.examen.app.service;

import com.examen.app.entity.Contenedor;
import com.examen.app.repository.ContenedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenedorService {

    @Autowired
    private ContenedorRepository contenedorRepository;

    public List<Contenedor> getAllContenedores() {
        return contenedorRepository.findAll();
    }

    public Contenedor getContenedorById(Long id) {
        Optional<Contenedor> contenedor = contenedorRepository.findById(id);
        if (contenedor.isPresent()) {
            return contenedor.get();
        } else {
            throw new RuntimeException("Contenedor no encontrado con ID: " + id);
        }
    }

    // Guardar un nuevo contenedor
    public Contenedor saveContenedor(Contenedor contenedor) {
        return contenedorRepository.save(contenedor);
    }

    // Actualizar un contenedor existente
    public Contenedor updateContenedor(Long id, Contenedor contenedorDetails) {
        Contenedor contenedor = getContenedorById(id);
        contenedor.setVin(contenedorDetails.getVin());
        contenedor.setFecha(contenedorDetails.getFecha());
        contenedor.setBarco(contenedorDetails.getBarco());
        return contenedorRepository.save(contenedor);
    }

    // Eliminar un contenedor
    public void deleteContenedor(Long id) {
        Contenedor contenedor = getContenedorById(id);
        contenedorRepository.delete(contenedor);
    }
}