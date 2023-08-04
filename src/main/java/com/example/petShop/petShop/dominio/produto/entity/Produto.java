package com.example.petShop.petShop.dominio.produto.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name="tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImagem;
    private double preco;

    public Produto(String nome, String descricao, String urlImagem, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }
    public Produto(){}

    public UUID getId() {
        return id;
    }
    public Produto setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }
    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }
    public Produto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUrlImagem() {
        return urlImagem;
    }
    public Produto setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
        return this;
    }

    public double getPreco() {
        return preco;
    }
    public Produto setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return Double.compare(produto.preco, preco) == 0 && Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(urlImagem, produto.urlImagem);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, urlImagem, preco);
    }
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                ", preco=" + preco +
                '}';
    }
}
