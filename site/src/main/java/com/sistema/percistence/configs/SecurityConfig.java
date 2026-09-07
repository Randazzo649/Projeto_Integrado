package com.sistema.percistence.configs;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class SecurityConfig {
    
    private static SecurityConfig conf;
    private static final int SALT_LENGTH = 16;
    private static final int HASH_LENGTH = 32;
    private static final int MEMORIA = 8192;
    private static final int PARALELISMO = 1;
    private static final int ITERACOES = 2;
    private static final Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, SALT_LENGTH, HASH_LENGTH);

    private  SecurityConfig(){}

    public static SecurityConfig getInstance(){
        if(conf == null)
            conf = new SecurityConfig();
        return conf;
    }

    public String hash(String palavra){
        return argon.hash(ITERACOES, MEMORIA, PARALELISMO, palavra.toCharArray());
    }

    public boolean verificar(String senhaBanco, String senhaInformada){
        return argon.verify(senhaBanco, senhaInformada.toCharArray());
    }

}
