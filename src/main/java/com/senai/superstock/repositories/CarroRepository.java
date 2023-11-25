package com.senai.superstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.superstock.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    
};
