package com.example.petShop.petShop.dominio.produto.service.exception;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg){
        super(msg);
    }
}
