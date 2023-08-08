package com.example.petShop.petShop.dominio.pessoa.repository;


import com.example.petShop.petShop.dominio.pessoa.entity.Pessoa;

import java.util.List;

public interface IPessoaRepository {
    List<Pessoa> findAll (int page, int pageSize);

    Pessoa findById (Long id);

    Pessoa Save(Pessoa pessoa);
    Pessoa update(Long id, Pessoa pessoa);

    void deleteById(Long id);
}
