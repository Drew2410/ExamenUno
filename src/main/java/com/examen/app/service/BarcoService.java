package com.examen.app.service;

import com.examen.app.entity.Barco;
import com.examen.app.repository.BarcoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarcoService {

    @Autowired
    private BarcoRepository barcoRepository;

    public List<Barco> getAllBarcos() {
        return barcoRepository.findAll();
    }

    public Barco getBarcoById(Long id) {
        Optional<Barco> barco = barcoRepository.findById(id);
        if (barco.isPresent()) {
            return barco.get();
        } else {
            throw new RuntimeException("Barco no encontrado con ID: " + id);
        }
    }

    public Barco saveBarco(Barco barco) {
        return barcoRepository.save(barco);
    }

    public Barco updateBarco(Long id, Barco barcoDetails) {
        Barco barco = getBarcoById(id);
        barco.setNombre(barcoDetails.getNombre());
        barco.setOrigen(barcoDetails.getOrigen());
        barco.setDestino(barcoDetails.getDestino());
        barco.setCapacidad(barcoDetails.getCapacidad());
        return barcoRepository.save(barco);
    }

    public void deleteBarco(Long id) {
        Barco barco = getBarcoById(id);
        barcoRepository.delete(barco);
    }
}