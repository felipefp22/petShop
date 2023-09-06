package com.example.petShop.petShop.dominio.categoria.entity;

import com.example.petShop.petShop.dominio.categoria.entity.dtoS.CategoriaDTO;
import com.example.petShop.petShop.dominio.produto.entity.Produto;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos = new HashSet<>();

    public Categoria() {}
    public Categoria(Long id, String nome, Instant dataDeCriacao, Set<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
        this.produtos = produtos;
    }
    public Categoria(CategoriaDTO categoriaDTO){
        this.id = categoriaDTO.id();
        this.nome = categoriaDTO.nome();
        this.dataDeCriacao = categoriaDTO.dataDeCriacao();
        this.produtos = categoriaDTO.produtos();
    }

    public CategoriaDTO toCategoriaDTO(){
        return new CategoriaDTO(this.id, this.nome, this.dataDeCriacao, this.produtos);
    }

    public void prePersist(){
        this.dataDeCriacao = Instant.now();
    }


    // ------------------------------------------------- \\

    // ---------- *** GETTERs e SETTERs *** ---------- \\
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
    public Set<Produto> getProdutos() {
        return produtos;
    }

    public Categoria setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
        return this;
    }

    // ------------------------------------------------- \\


    // ---------- *** EQUALs | HASH | toSTRING *** ---------- \\
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

    // ------------------------------------------------- \\


}
