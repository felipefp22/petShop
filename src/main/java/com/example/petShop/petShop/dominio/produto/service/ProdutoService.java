package com.example.petShop.petShop.dominio.produto.service;

import com.example.petShop.petShop.dominio.produto.dto.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import com.example.petShop.petShop.dominio.produto.repository.IProdutoRepository;
import com.example.petShop.petShop.dominio.produto.service.exception.ControllerNotFoundException;
import com.example.petShop.petShop.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repo;

    public Page<ProdutoDTO> findAll(PageRequest pagina){
        var produtos = repo.findAll(pagina);

        return produtos.map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco()));
    }

    public ProdutoDTO findById(UUID id){
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não Encontrado"));

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto entity = new Produto();
        entity.setNome(produtoDTO.nome());
        entity.setDescricao(produtoDTO.descricao());
        entity.setPreco(produtoDTO.preco());

        repo.save(entity);
        return new ProdutoDTO(entity.getId(), entity.getNome(), produtoDTO.descricao(), entity.getPreco());
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDTO){
        try {
            Produto buscaProduto = repo.getOne(id);

            buscaProduto.setNome(produtoDTO.nome());
            buscaProduto.setDescricao(produtoDTO.descricao());
            buscaProduto.setPreco(produtoDTO.preco());

            buscaProduto = repo.save(buscaProduto);

            return new ProdutoDTO(buscaProduto.getId(), buscaProduto.getNome(), buscaProduto.getDescricao(), buscaProduto.getPreco());
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Produto não Encontrado");
        }
    }

    public void delete(UUID id) {
        try{
            repo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Violação de Integridade da Base - ID: " + id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de Integridade da Base");
        }
    }
}
