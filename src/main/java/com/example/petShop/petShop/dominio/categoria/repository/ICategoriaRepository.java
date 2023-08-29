package com.example.petShop.petShop.dominio.categoria.repository;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

}
