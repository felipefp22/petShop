package com.example.petShop.petShop.dominio.produto.entity.dtoS;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Set;
import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        @NotBlank(message = "Nome Obrigatório")
        String nome,
        String descricao,
        String urlImage,
        Set<Categoria> categorias,
        @Positive(message = "O Preço precisa ser Positivo!")
        double preco

){
    public ProdutoDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getUrlImagem(), produto.getCategorias(), produto.getPreco());
    }

    public Produto toProduto(){

          return new Produto(this.nome, this.descricao,this.urlImage, this.preco, this.categorias);
    }
}
