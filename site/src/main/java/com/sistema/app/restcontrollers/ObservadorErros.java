package com.sistema.app.restcontrollers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ObservadorErros {
    
     @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> tratarErroDeBanco(SQLException e) {
        System.out.println("______________SQL EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("0");
    }

}
