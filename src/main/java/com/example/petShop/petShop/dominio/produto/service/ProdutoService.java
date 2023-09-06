package com.example.petShop.petShop.dominio.produto.service;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.categoria.repository.ICategoriaRepository;
import com.example.petShop.petShop.dominio.produto.entity.dtoS.ProdutoDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import com.example.petShop.petShop.dominio.produto.repository.IProdutoRepository;
import com.example.petShop.petShop.exception.Service.ControllerNotFoundException;
import com.example.petShop.petShop.exception.Service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository produtoRepo;
    private ICategoriaRepository categoriaRepo;

    public Page<ProdutoDTO> findAll(PageRequest pagina){
        var produtos = produtoRepo.findAll(pagina);

        return produtos.map(produto -> new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(), null, produto.getCategorias(), produto.getPreco()));
    }

    public ProdutoDTO findById(UUID id){
        var produto = produtoRepo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não Encontrado"));

        return new ProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {

        Produto entity = produtoDTO.toProduto();

        return new ProdutoDTO(produtoRepo.save(entity));
    }

    public ProdutoDTO update(UUID id, ProdutoDTO produtoDTO){
        try {
            Produto buscaProduto = produtoRepo.getOne(id);

            buscaProduto.setNome(produtoDTO.nome());
            buscaProduto.setDescricao(produtoDTO.descricao());
            buscaProduto.setPreco(produtoDTO.preco());

            Set<Categoria> categorias = convetCategorias(produtoDTO);
            categorias.forEach(categoria -> buscaProduto.getCategorias().add((categoria)));

            return new ProdutoDTO(produtoRepo.save(buscaProduto));
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

    public Set<Categoria> convetCategorias(ProdutoDTO produtoDTO){
        Set<Categoria> categorias = produtoDTO.categorias();

        if (produtoDTO.categorias() != null)
            categorias = produtoDTO.categorias().stream()
                    .map(categoriaId -> categoriaRepo.findById(categoriaId.getId())
                            .orElseThrow(() -> new RuntimeException("Consumidor não encontrado com ID: " + categoriaId.getId())))
                    .collect(Collectors.toSet());

        return categorias;
    }
}
