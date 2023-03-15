package com.projeto.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.springboot.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
    
}
