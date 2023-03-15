package com.projeto.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.springboot.model.Contato;
import com.projeto.springboot.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoRepository repository;
    
    @GetMapping
    public List<Contato> listar() {

        return repository.findAll();

    }

    @PostMapping
    public Contato adicionar(@RequestBody Contato contato) {
       return repository.save(contato);
    }

    

}
