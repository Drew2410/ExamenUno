package com.examen.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contenedores")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Contenedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "vin", nullable = false, unique = true, length = 50)
    private String vin;
    
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name = "id_barco", nullable = false)
    @JsonIgnore
    private Barco barco;

    @Override
    public String toString() {
        return "Contenedor{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", fecha=" + fecha +
                ", barco=" + barco +
                '}';
    }
}