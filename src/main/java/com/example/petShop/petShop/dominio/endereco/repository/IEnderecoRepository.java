package com.example.petShop.petShop.dominio.endereco.repository;

import com.example.petShop.petShop.dominio.endereco.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends JpaRepository<Endereco, Long> {

}
