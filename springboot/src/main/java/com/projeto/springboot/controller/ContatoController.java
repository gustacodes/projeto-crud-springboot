package com.projeto.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/listarporid/{id}")
    public ResponseEntity<Contato> listarPorId(@PathVariable long id) {

        Contato contato = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato"));

                return ResponseEntity.ok(contato);            

    }

    @GetMapping("/listarpornome/{nome}")
    public Contato findByNome(@PathVariable String nome, RedirectAttributes attributes) {

        Contato contato = repository.findByNome(nome);

        if(contato == null) {
            attributes.addFlashAttribute("Mensagem", "Contato não encontrado");
            return null;
        }

        return contato;

    }

    @PostMapping
    public Contato adicionar(@RequestBody Contato contato) {
       return repository.save(contato);
    }

    @PutMapping("/atualizarcontato/{id}")
    public ResponseEntity<Contato> alterar(@PathVariable long id, @RequestBody Contato contato) {

        Contato usuario = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato não existe: " + id));

        usuario.setNome(contato.getNome());
        usuario.setEmail(contato.getEmail());
        usuario.setTelefone(contato.getTelefone());
        usuario.setUrlAvatar(contato.getUrlAvatar());
        
        repository.save(usuario);
        
        return ResponseEntity.ok(usuario);

     }

    @DeleteMapping("/deletarcontato/{id}")
    public String deletar(@PathVariable("id") long id) {

       if(id > 0){
            repository.deleteById(id);
           return "Removido com sucesso";
        } else {
           return "Contato não encontrado"; 
        }
        
     }

}
