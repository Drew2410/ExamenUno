package com.examen.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.app.entity.Contenedor;


public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {
}