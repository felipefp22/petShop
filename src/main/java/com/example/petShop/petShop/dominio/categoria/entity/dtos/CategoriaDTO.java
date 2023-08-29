package com.example.petShop.petShop.dominio.categoria.entity.dtos;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;

import java.time.Instant;

public record CategoriaDTO(
        Long id,
        String nome,
        Instant dataDeCriacao
    ){
    public CategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getDataDeCriacao());
    }
}
