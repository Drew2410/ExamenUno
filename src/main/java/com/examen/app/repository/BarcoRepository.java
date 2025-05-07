package com.examen.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.app.entity.Barco;


public interface BarcoRepository extends JpaRepository<Barco, Long> {
}
