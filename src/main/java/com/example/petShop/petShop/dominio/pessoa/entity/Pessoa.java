package com.example.petShop.petShop.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa {
    private Long id;
    private String nome;
    private LocalDate nascimento;

    public Pessoa() {
    }
    public Pessoa(Long id, String nome, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Long getId() {
        return id;
    }
    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }
    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }
    public String getNome() {
        return nome;
    }

    public Pessoa setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }
    public LocalDate getNascimento() {
        return nascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id.equals(pessoa.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nascimento=" + nascimento +
                '}';
    }
}
