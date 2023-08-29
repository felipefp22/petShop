package com.example.petShop.petShop.dominio.categoria.entity.dtos;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.produto.entity.Produto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public record CategoriaDTO(
        Long id,
        String nome,
        Instant dataDeCriacao,
        Set<Produto> produtos


    ){
    public CategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getDataDeCriacao());
    }
}
