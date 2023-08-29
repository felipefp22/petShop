package com.example.petShop.petShop.dominio.produto.dto;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.categoria.entity.dtos.CategoriaDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        @NotBlank(message = "Nome Obrigatório")
        String nome,
        String descricao,
        String urlImage,
        Set<CategoriaDTO> categorias,
        @Positive(message = "O Preço precisa ser Positivo!")
        double preco

){
    public ProdutoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        produto.getCategorias().forEach(categoria -> this.categorias().add(new CategoriaDTO(categoria)));
        this.preco = produto.getPreco();
    }

    public Produto toProduto(){
        Set<Categoria> categoriaEntity = new HashSet<>();

        this.categorias.forEach(categoria -> categoriaEntity.add(new Categoria(categoria)));

          return new Produto(this.nome, this.descricao,this.urlImage, this.preco, categoriaEntity);
    };
}
