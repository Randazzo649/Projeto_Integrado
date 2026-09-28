package com.sistema.app.errors;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

import jakarta.mail.MessagingException;

@RestControllerAdvice 
public class ObservadorErros {

    private ModelAndView enviarStackTraceParaFront(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        ModelAndView mv = new ModelAndView("erro");
        mv.addObject("stacktrace", sw.toString());

        return mv;
    }
    
    @ExceptionHandler(SQLException.class)
    public ModelAndView tratarErroDeBanco(SQLException e) {
        System.out.println("______________SQL EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________");
        return enviarStackTraceParaFront(e);
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView tratarErroDeBanco(IOException e) {
        System.out.println("______________IO EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("________________________________________");
        return enviarStackTraceParaFront(e);
    }

    @ExceptionHandler(MessagingException.class)
    public ModelAndView tratarErroDeEnvioDeEmail(MessagingException e) {
        System.out.println("______________EMAIL EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("__________________________________________");
        return enviarStackTraceParaFront(e);
    }

    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView tratarErroDoThymeleaf(TemplateInputException e) {
        System.out.println("______________THYMELEAF EXCEPTION_____________");
        System.out.println(e.getMessage());
        System.out.println("______________________________________________");
        return enviarStackTraceParaFront(e);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView tratarExcessaoGenerica(Exception e){
        return enviarStackTraceParaFront(e);
    }

}
