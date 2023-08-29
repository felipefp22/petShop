package com.example.petShop.petShop.dominio.produto.service;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.categoria.entity.dtos.CategoriaDTO;
import com.example.petShop.petShop.dominio.categoria.repository.ICategoriaRepository;
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
    private IProdutoRepository produtoRepo;
    private ICategoriaRepository categoriaRepo;

    public Page<ProdutoDTO> findAll(PageRequest pagina){
        var produtos = produtoRepo.findAll(pagina);

        return produtos.map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco()));
    }

    public ProdutoDTO findById(UUID id){
        var produto = produtoRepo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não Encontrado"));

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        Produto entity = new Produto();
        entity.setNome(produtoDTO.nome());
        entity.setDescricao(produtoDTO.descricao());
        entity.setPreco(produtoDTO.preco());

        produtoRepo.save(entity);
        return new ProdutoDTO(entity.getId(), entity.getNome(), produtoDTO.descricao(), entity.getPreco());
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDTO){
        try {
            Produto buscaProduto = produtoRepo.getOne(id);

            buscaProduto.setNome(produtoDTO.nome());
            buscaProduto.setDescricao(produtoDTO.descricao());
            buscaProduto.setPreco(produtoDTO.preco());
            produtoDTO.categorias().forEach(categoria -> buscaProduto.getCategorias().add(new Categoria(categoria)));
            buscaProduto = produtoRepo.save(buscaProduto);

            return new ProdutoDTO(buscaProduto.getId(), buscaProduto.getNome(), buscaProduto.getDescricao(), buscaProduto.getPreco(), buscaProduto.getCategorias());
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Produto não Encontrado");
        }
    }

    public void delete(UUID id) {
        try{
            produtoRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Violação de Integridade da Base - ID: " + id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de Integridade da Base");
        }
    }
}
