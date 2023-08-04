package com.example.petShop.petShop.dominio.produto.controller;

import com.example.petShop.petShop.dominio.produto.dto.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import com.example.petShop.petShop.dominio.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findAll(@RequestParam(value = "pagina", defaultValue = "0")Integer pagina, @RequestParam(value = "tamanho", defaultValue = "10")Integer tamanho){
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);

        var produtos = produtoService.findAll(pageRequest);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable UUID id){
        var produtos = produtoService.findById(id);

        return ResponseEntity.ok(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> save(@Valid @RequestBody ProdutoDTO produtoDTO){
        var produtoSaved = produtoService.save(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand((produtoSaved.id())).toUri();

        return ResponseEntity.created(uri).body(produtoSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable UUID id, @RequestBody ProdutoDTO produtoDTO){
        var produtoUpdated = produtoService.update(id, produtoDTO);

        return ResponseEntity.ok(produtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        produtoService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
