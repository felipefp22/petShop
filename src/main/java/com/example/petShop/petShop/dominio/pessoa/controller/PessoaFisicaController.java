package com.example.petShop.petShop.dominio.pessoa.controller;

import com.example.petShop.petShop.dominio.pessoa.entity.PessoaFisica;
import com.example.petShop.petShop.dominio.pessoa.repository.PessoaFisicaCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/pf")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaCollectionRepository repo;

    @GetMapping
    public ResponseEntity<Collection<PessoaFisica>> findAll(){
        var pessoas = repo.findAll();

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PessoaFisica>> findById(@PathVariable Long id){
        var pessoaFisica = this.repo.findById(id);
        return ResponseEntity.ok(pessoaFisica);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody PessoaFisica pessoaFisica){
        repo.save(pessoaFisica);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Optional<PessoaFisica>> update(@RequestBody PessoaFisica pessoaFisica){
        Optional<PessoaFisica> pessoaAdicionada = repo.update(pessoaFisica);
        return ResponseEntity.ok(pessoaAdicionada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        repo.delete(id);
        return ResponseEntity.ok("Delete com sucesso");
    }
}
