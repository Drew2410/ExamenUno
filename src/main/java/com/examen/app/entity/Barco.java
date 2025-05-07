package com.examen.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "barcos")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Barco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "origen", nullable = false, length = 100)
    private String origen;
    
    @Column(name = "destino", nullable = false, length = 100)
    private String destino;
    
    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @OneToMany(mappedBy = "barco", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Contenedor> contenedores;

    @Override
    public String toString() {
        return "Barco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }
}