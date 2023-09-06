package com.example.petShop.petShop.dominio.produto.repository;

import com.example.petShop.petShop.dominio.produto.entity.Produto;
import com.example.petShop.petShop.exception.Service.ControllerNotFoundException;
import com.example.petShop.petShop.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private Long countTotalProdutos;
    private String nomeAtualizado;

    @BeforeEach
    void setUp() throws Exception{
        idExistente = UUID.fromString("852b168d-668e-4220-8af8-32b3dc5b0fcc");
        idNaoExistente = UUID.fromString("852b168d-668e-4220-8af8-32b3dc5b0fc");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5l;
        nomeAtualizado = "Atualização Nome Produto";
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados(){
        Page produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(produtos.getTotalElements(), this.countTotalProdutos);
    }

    @Test
    public void FindByIdDeveRetornarObjetoCasoIdExista(){
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertTrue(result.isPresent());
    }
    @Test
    public void findByIdDeveRetornarControllerNotFoundExeptionCasoIdNaoExiste(){
        Assertions.assertThrows(ControllerNotFoundException.class, () ->
        {produtoRepository.findById(this.idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Produto não Encontrado"));
        });
    }
    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull(){
        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo);
    }

    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull(){
        Produto produto = Factory.createProduto();
        produto.setId(this.idExistente);
        produto.setNome(this.nomeAtualizado);

        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertEquals(produtoSalvo.getNome(), this.nomeAtualizado);
    }

    @Test
    public void deletarObjetoCasoExista(){
        produtoRepository.deleteById(this.idExistente);
        Optional<Produto> result = produtoRepository.findById(this.idExistente);
        Assertions.assertFalse(result.isPresent());
    }
}
