package com.example.petShop.petShop.dominio.produto.controller;

import com.example.petShop.petShop.dominio.produto.entity.dtoS.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProdutoService produtoService;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOCasoIdExiste() throws Exception{
        UUID id = UUID.fromString("852b168d-668e-4220-8af8-32b3dc5b0fcc");

        ProdutoDTO produto = new ProdutoDTO(id, "PC", "Pc Gamer", 50000.00 );

        Mockito.when(produtoService.findById(id)).thenReturn(produto);

        ResultActions result = mockMvc.perform(get("/produtos/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());

        
    }

}
