package com.example.petShop.petShop.dominio.produto.dto;

import com.example.petShop.petShop.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        @NotBlank(message = "Nome Obrigatório")
        String nome,
        String descricao,
        @Positive(message = "O Preço precisa ser Positivo!")
        double preco
){

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setId(this.id);
        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPreco(this.preco);
        return produto;
    };
}
