package com.example.petShop.petShop.dominio.endereco.entity.dtoS;

import com.example.petShop.petShop.dominio.endereco.entity.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(

        Long id,
        @NotBlank(message = "A rua não pode estar em Branco")
        String rua,
        @NotBlank(message = "A cidade não pode estar em Branco")
        String cidade,
        @NotBlank (message = "O estado não pode estar em Branco")
        @Size(min = 2, max = 2, message = "O estado deve ter apenas 2 caracteres")
        String estado,
        @NotBlank(message = "O CEP não pode estar em Branco")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve esta no formato = 12345-123")
        String cep
) {
    public EnderecoDTO(Endereco endereco){
        this(endereco.getId(), endereco.getRua(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
    }
    public Endereco toEntity (){
        return new Endereco(this);
    }

}
