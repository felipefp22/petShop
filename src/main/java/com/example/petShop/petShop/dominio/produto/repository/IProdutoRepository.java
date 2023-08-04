package com.example.petShop.petShop.dominio.produto.repository;

import com.example.petShop.petShop.dominio.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, UUID> {
}
