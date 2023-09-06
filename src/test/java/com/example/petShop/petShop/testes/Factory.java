package com.example.petShop.petShop.testes;

import com.example.petShop.petShop.dominio.produto.entity.dtoS.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;

public class Factory {

    public static Produto createProduto(){
        return new Produto("Iphone", "XR", "URL Ios", 4000.00);
    }

    public static ProdutoDTO createProdutoDto(){
        Produto produto = createProduto();

        return new ProdutoDTO(createProduto().getId(), createProduto().getNome(), createProduto().getDescricao(), createProduto().getPreco());
    }
}
