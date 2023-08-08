package com.example.petShop.petShop.dominio.pessoa.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private Long id;
    private String nome;
    private LocalDate nascimento;
    private String cpf;
    private String email;

    public Pessoa() {
    }
    public Pessoa(Long id, String nome, LocalDate nascimento, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.email = email;
    }

    // ------------ GET e SET - [ID] -----------
    public Long getId() {
        return id;
    }
    public Pessoa setId(Long id) {
        this.id = id;
        return this;
    }

    // ------------ GET e SET - [NOME] -----------
    public String getNome() {
        return nome;
    }
    public Pessoa setNome(String nome) {
        this.nome = nome;
        return this;
    }

    // ------------ GET e SET - [NASCIMENTO] -----------
    public LocalDate getNascimento() {
        return nascimento;
    }
    public Pessoa setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    // ------------ GET e SET - [CPF] -----------
    public String getCpf() {
        return cpf;
    }
    public Pessoa setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    // ------------ GET e SET - [EMAIL] -----------
    public String getEmail() {
        return email;
    }
    public Pessoa setEmail(String email) {
        this.email = email;
        return this;
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
