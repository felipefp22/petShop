package com.example.petShop.petShop.dominio.categoria.entity;

import com.example.petShop.petShop.dominio.categoria.entity.dtos.CategoriaDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

    public Categoria() {}
    public Categoria(Long id, String nome, Instant dataDeCriacao) {
        id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
        return id;
    }
    public Categoria setId(Long id) {
        id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }
    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }
    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }
    public Categoria setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public CategoriaDTO toCategoriaDTO(){
        return new CategoriaDTO(this.id, this.nome, this.dataDeCriacao);
    }

    public void prePersist(){
        this.dataDeCriacao = Instant.now();
    }
}
