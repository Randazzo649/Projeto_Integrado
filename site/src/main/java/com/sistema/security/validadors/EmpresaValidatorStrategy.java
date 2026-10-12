
package com.sistema.security.validadors;

import com.sistema.models.Empresa;
import com.sistema.security.ValidacaoStrategy;

public class EmpresaValidatorStrategy implements ValidacaoStrategy<Empresa> {

    @Override
    public boolean isValido(Empresa entidade) {
        if (entidade == null)
            return false;
        if (entidade.getRazao() == null || entidade.getRazao().isBlank() || entidade.getRazao().length() > 200)
            return false;
        if (!cnpjValido(entidade.getCnpj()))
            return false;
        if (entidade.getTelefone() == null || entidade.getTelefone().isBlank() || entidade.getTelefone().length() > 20)
            return false;
        if (entidade.getEndereco() == null || entidade.getEndereco().isBlank() || entidade.getEndereco().length() > 255)
            return false;
        if (entidade.getFoto() == null || entidade.getFoto().isBlank() || entidade.getFoto().length() > 255)
            return false;
        if (!corValida(entidade.getCor()))
            return false;
        if (entidade.getNome() == null || entidade.getNome().isBlank() || entidade.getNome().length() > 150)
            return false;

        return true;
    }

    private boolean cnpjValido(String cnpj) {
        if (cnpj == null) 
            return false;

        String numeros = cnpj.replaceAll("\\D", "");
        if (!numeros.matches("\\d{14}") || numeros.matches("(\\d)\\1{13}"))
            return false;

        int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int soma = 0;

        for (int i = 0; i < 12; i++)
            soma += (numeros.charAt(i) - '0') * pesos1[i];

        int digito = soma % 11;
        digito = digito < 2 ? 0 : 11 - digito;
        if (digito != numeros.charAt(12) - '0')
            return false;

        soma = 0;
        for (int i = 0; i < 13; i++)
            soma += (numeros.charAt(i) - '0') * pesos2[i];

        digito = soma % 11;
        digito = digito < 2 ? 0 : 11 - digito;

        return digito == numeros.charAt(13) - '0';
    }

    private boolean corValida(String cor) {
        return cor != null && cor.matches("#[0-9a-fA-F]{6}");
    }

}
