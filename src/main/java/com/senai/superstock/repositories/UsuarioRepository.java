package com.senai.superstock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.superstock.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    public Usuario findByCpf(String cpf);
};
