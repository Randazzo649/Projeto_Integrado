package com.sistema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sistema.percistence.configs.FilePersistenceConfig;


@SpringBootApplication()
public class Main{
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
        FilePersistenceConfig.getInstance().configurarDiretorios();
        System.out.println("_____________________");
        System.out.println("rodando em: http://127.0.0.1:8080/");
    }
}