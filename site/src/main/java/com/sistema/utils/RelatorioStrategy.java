package com.sistema.utils;

import java.util.List;
import org.springframework.ui.Model;

public interface RelatorioStrategy<T> {

    public void adicionarIndicadores(Model m, List<T> entidades);
    
}
