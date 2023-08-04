package com.example.petShop.petShop.dominio.pessoa.repository;

import com.example.petShop.petShop.dominio.pessoa.entity.PessoaFisica;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaFisicaCollectionRepository {

    static private Set<PessoaFisica> pessoas;

    static {
        pessoas = new LinkedHashSet<>();

        PessoaFisica p1 = new PessoaFisica();

        p1.setCpf("50369804213").setNome("Joao Bastos").setNascimento(LocalDate.of(1977,3,8));

        PessoaFisica dep1 = new PessoaFisica();
        dep1.setCpf("42635189752").setNome("Maria das Flores").setNascimento(LocalDate.of(2000, 5,7));

        p1.addDependent(dep1);

        save(p1);
    }

    public Collection<PessoaFisica> findAll(){
        return pessoas;
    }

    public Optional<PessoaFisica> findById(Long id){
        return pessoas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public static PessoaFisica save(@NotNull PessoaFisica p) {
        p.setId(pessoas.size() + 1L);
        pessoas.add(p);
        return p;
    }

    public Optional<PessoaFisica> update(PessoaFisica pessoaFisica){
        Optional<PessoaFisica> pessoaASerBuscada = this.findById(pessoaFisica.getId());

        if(pessoaASerBuscada.isPresent()){
            PessoaFisica pessoa = pessoaASerBuscada.get();
            pessoa.setCpf(pessoaFisica.getCpf());
            pessoa.setNome(pessoaFisica.getNome());
            pessoa.setNascimento(pessoaFisica.getNascimento());

            return Optional.of(pessoa);
        }
        return Optional.empty();
    }

    public void delete(Long id){
        pessoas.removeIf(p -> p.getId().equals(id));
    }
}
