package com.example.petShop.petShop.dominio.endereco.entity;

import com.example.petShop.petShop.dominio.endereco.entity.dtoS.EnderecoDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name="tb_endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(Long id, String cidade, String estado, String cep) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
    public Endereco(EnderecoDTO enderecoDTO){
        this(enderecoDTO.id(), enderecoDTO.cidade(), enderecoDTO.estado(), enderecoDTO.cep());
    }

    // ---------- *** GETTERs e SETTERs *** ---------- \\

    public Long getId() {
        return id;
    }
    public Endereco setId(Long id) {
        this.id = id;
        return this;
    }
    public String getRua() {
        return rua;
    }
    public Endereco setRua(String rua) {
        this.rua = rua;
        return this;
    }

    public String getCidade() {
        return cidade;
    }
    public Endereco setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }
    public Endereco setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public String getCep() {
        return cep;
    }
    public Endereco setCep(String cep) {
        this.cep = cep;
        return this;
    }

    // ------------------------------------------------- \\

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua) && Objects.equals(cidade, endereco.cidade) && Objects.equals(estado, endereco.estado) && Objects.equals(cep, endereco.cep);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, rua, cidade, estado, cep);
    }
    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", rua='" + rua + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
// ------------------------------------------------- \\
}
