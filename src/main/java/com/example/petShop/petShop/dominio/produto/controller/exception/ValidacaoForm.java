package com.example.petShop.petShop.dominio.produto.controller.exception;

import com.example.petShop.petShop.dominio.produto.service.exception.DefaultError;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoForm extends DefaultError {

    private List<ValidacaoCampo> mensagens = new ArrayList<ValidacaoCampo>();

    public List<ValidacaoCampo> getMensagens(){
        return mensagens;
    }

    public void addMensagens(String campo, String mensagem){
        mensagens.add(new ValidacaoCampo(campo, mensagem));
    }

}
