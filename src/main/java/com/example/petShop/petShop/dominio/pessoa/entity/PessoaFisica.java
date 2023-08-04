package com.example.petShop.petShop.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class PessoaFisica extends Pessoa {

    private String cpf;
    private Collection<PessoaFisica> dependentes = new Vector<>();

    public PessoaFisica(Long id, String nome, LocalDate nascimento, String cpf, Collection<PessoaFisica> dependentes) {
        super(id, nome, nascimento);
        this.cpf = cpf;
        this.dependentes = dependentes;
    }
    public PessoaFisica(){
    }

    public String getCpf() {
        return cpf;
    }
    public PessoaFisica setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PessoaFisica addDependent(PessoaFisica p){
        dependentes.add(p);
        return this;
    }
    public PessoaFisica removeDependent(PessoaFisica p){
        dependentes.remove(p);
        return this;
    }

    public Collection<PessoaFisica> getDependentes(){
        return Collections.unmodifiableCollection(dependentes);
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "dependentes=" + dependentes +
                '}';
    }
}
