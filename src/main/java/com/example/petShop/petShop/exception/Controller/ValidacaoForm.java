package com.example.petShop.petShop.exception.Controller;

import com.example.petShop.petShop.exception.Service.DefaultError;

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
