package com.example.petShop.petShop.dominio.produto.service;

import com.example.petShop.petShop.dominio.produto.dto.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import com.example.petShop.petShop.dominio.produto.repository.IProdutoRepository;
import com.example.petShop.petShop.dominio.produto.service.exception.ControllerNotFoundException;
import com.example.petShop.petShop.testes.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService service;
    @Mock
    private IProdutoRepository repository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private Produto produto;
    private ProdutoDTO produtoDTO;
    private PageImpl<Produto> page;
    private String nomeAtualizado;

    @BeforeEach
    public void setUp(){
        idExistente = UUID.fromString("852b168d-668e-4220-8af8-32b3dc5b0fcc");
        idNaoExistente = UUID.fromString("852b168d-668e-4220-8af8-32b3dc5b0fc");
        pageRequest = PageRequest.of(0, 10);
        produto = Factory.createProduto();
        produtoDTO = Factory.createProdutoDto();
        page = new PageImpl<>(List.of(produto));
        nomeAtualizado = "Nome Produto Atualizado";

        Mockito.when(repository.findById((UUID) ArgumentMatchers.any())).thenReturn(Optional.of(produto));
        Mockito.when(repository.findAll((PageRequest) ArgumentMatchers.any())).thenReturn(page);
        Mockito.when(repository.findById(idNaoExistente)).thenReturn(Optional.empty());
    }

    @Test
    public void findAllDeveRetornarUmaListaDeProdutosDTO(){
        Page produtoDto = service.findAll(this.pageRequest);
        Assertions.assertNotNull(produtoDto);
    }
    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarOId(){
        ProdutoDTO produtosDTO = service.findById(this.idExistente);
        Assertions.assertNotNull(produtosDTO);
    }
    @Test
    public void FindByIdDeveRetornarUmaExcessaoAoBuscarPorIdQueNaoExiste(){
        Assertions.assertThrows(ControllerNotFoundException.class, () ->{
            service.findById(this.idNaoExistente);
        });
    }
}
