package com.sistema.security.validadors;

import java.util.regex.Pattern;
import com.sistema.models.Funcionario;
import com.sistema.security.ValidacaoStrategy;

public class FuncionarioValidatorStrategy implements ValidacaoStrategy<Funcionario> {

    private static final Pattern EMAIL = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    @Override
    public boolean isValido(Funcionario entidade) {
        
        if (entidade == null)
            return false;
        if (!textoValido(entidade.getNome(), 150))
            return false;
        if (!emailValido(entidade.getEmail()))
            return false;
        if (!textoValido(entidade.getSenha(), 128))
            return false;
        if (!textoValido(entidade.getCargo(), 100))
            return false;
        if (!dataValida(entidade.getData_admissao()) || !dataValida(entidade.getData_nascimento()))
            return false;
        if (!textoValido(entidade.getEstado(), 50))
            return false;
        if (!textoValido(entidade.getEndereco(), 255))
            return false;
        if (!cpfValido(entidade.getCpf()))
            return false;
        if (!textoValido(entidade.getTelefone(), 20))
            return false;


        return true;
    }

    private boolean textoValido(String texto, int limite) {
        return texto != null && !texto.isBlank() && texto.length() <= limite;
    }

    private boolean emailValido(String email) {
        return textoValido(email, 254)&& EMAIL.matcher(email).matches();
    }


    private boolean dataValida(String data) {
        if (!textoValido(data, 10)) 
            return false;
        return true;
    }

    private boolean cpfValido(String cpf) {


        if (cpf == null || cpf.trim().isEmpty()) 
            return false;
        

        String numeros = cpf.replaceAll("\\D", "");
        if (!numeros.matches("\\d{11}")) 
            return false;
        if (numeros.matches("(\\d)\\1{10}")) 
            return false;
        

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (numeros.charAt(i) - '0') * (10 - i);
        }

        int digito = 11 - soma % 11;
        digito = digito >= 10 ? 0 : digito;

        if (digito != numeros.charAt(9) - '0') {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (numeros.charAt(i) - '0') * (11 - i);
        }
        
        digito = 11 - soma % 11;
        digito = digito >= 10 ? 0 : digito;

        return digito == numeros.charAt(10) - '0';
    }

}
