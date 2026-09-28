package com.sistema.app.errors;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.mail.MessagingException;

@RestControllerAdvice 
public class ObservadorErros {
    
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> tratarErroDeBanco(SQLException e) {
        System.out.println("______________SQL EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("0");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> tratarErroDeBanco(IOException e) {
        System.out.println("______________IO EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("0");
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<String> tratarErroDeEnvioDeEmail(MessagingException e) {
        System.out.println("______________EMAIL EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("__________________________________________");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("0");
    }

}
