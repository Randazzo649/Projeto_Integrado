package com.sistema.security;

public interface ValidacaoStrategy<T>{

    boolean isValido(T entidade);

}