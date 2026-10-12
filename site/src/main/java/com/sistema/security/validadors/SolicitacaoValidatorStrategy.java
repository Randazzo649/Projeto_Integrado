
package com.sistema.security.validadors;

import com.sistema.models.Solicitacao;
import com.sistema.models.Modulo;
import com.sistema.security.ValidacaoStrategy;

public class SolicitacaoValidatorStrategy implements ValidacaoStrategy<Solicitacao> {

    private final String padraoEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    @Override
    public boolean isValido(Solicitacao entidade) {
        if (entidade == null)
            return false;
        if (!textoValido(entidade.getNome(), 150))
            return false;
        if (!textoValido(entidade.getRazaoSocial(), 200))
            return false;
        if (!textoValido(entidade.getCnpj(), 18))
            return false;
        if (!textoValido(entidade.getTelefone(), 20))
            return false;
        if (!textoValido(entidade.getEndereco(), 255))
            return false;
        if (!emailValido(entidade.getEmail()))
            return false;
        if (!textoValido(entidade.getSenha(), 128))
            return false;
        if (entidade.getModulos() == null || entidade.getModulos().length == 0) 
            return false;
        
        for (Modulo modulo : entidade.getModulos()) 
            if (modulo == null) 
                return false;
        
        return true;
    }

    private boolean textoValido(String texto, int limite) {
        return texto != null && !texto.isBlank() && texto.length() <= limite;
    }

    private boolean emailValido(String email) {
        return textoValido(email, 254) && email.matches(padraoEmail);
    }
}
