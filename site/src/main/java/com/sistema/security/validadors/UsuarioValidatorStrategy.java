package com.sistema.security.validadors;

import com.sistema.models.Usuario;
import com.sistema.security.ValidacaoStrategy;

public class UsuarioValidatorStrategy implements ValidacaoStrategy<Usuario> {

    private static final String padraoEmail = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";

    @Override
    public boolean isValido(Usuario entidade) {
        if (entidade == null)
            return false;
        if (entidade.getNome() == null || entidade.getNome().isBlank() || entidade.getNome().length() > 150)
            return false;
        if (entidade.getEmail() == null || entidade.getEmail().length() > 254 || !entidade.getEmail().matches(padraoEmail))
            return false;
        if (entidade.getSenha() == null || entidade.getSenha().isBlank() || entidade.getSenha().length() > 128)
            return false;
        if (entidade.getFoto() == null || entidade.getFoto().isBlank() || entidade.getFoto().length() > 255)
            return false;
        if (entidade.getFuncao() == null || entidade.getFuncao().isBlank() || entidade.getFuncao().length() > 100)
            return false;
        if (!entidade.isCurador() && entidade.getIdEmpresa() <= 0)
            return false;

        return true;
    }
}