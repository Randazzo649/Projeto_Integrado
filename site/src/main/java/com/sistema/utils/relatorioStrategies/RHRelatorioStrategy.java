package com.sistema.utils.relatorioStrategies;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.sistema.utils.RelatorioStrategy;
import com.sistema.models.Funcionario;


public class RHRelatorioStrategy implements RelatorioStrategy<Funcionario> {

    //IMPORTANTE !!!! as chaves deste Map usadas em atualizarMapDeFuncionarios 
    //devem ser iguais aos estados dos funcionarios
    //no banco de dados, elas também são iguais as referencias passadas ao Model/thymeleaf
    private Map<String, Integer> dadosFuncionarios = new HashMap<>(){{
        put("f_total", 0);
        put("ativo", 0);
        put("inativo", 0);
        put("f_admissoes", 0);
        put("desligado", 0);
        put("licenca", 0);
        put("ferias", 0);
    }};

    private Map<String, Integer> departamento_funcionario = new HashMap<>();
    //variaveis uteis
    private LocalDate dataAtual = LocalDate.now();


    //______________METODO PRINCIPAL

    @Override
    public void adicionarIndicadores(Model m, List<Funcionario> funcionarios) {
        
        this.dadosFuncionarios.put("f_total", funcionarios.size());
        for(Funcionario f : funcionarios){
            atualizarMapDeFuncionarios(f);
            atualizarNumeroDeAdmissoesNoMes(f);
            atualizarNumeroDeFuncionariosPorDepartamento(f);
        }

        //PARTE MAIS IMPORTANTE, quando o model é configurado:
        for(String s : this.dadosFuncionarios.keySet())
            m.addAttribute(s, this.dadosFuncionarios.get(s));
        m.addAttribute("departamento_funcionarios", this.departamento_funcionario);
    }

    
    
    
    //_______________METODOS PRIVADOS

    private void atualizarMapDeFuncionarios(Funcionario f){
            for(String s : this.dadosFuncionarios.keySet())
                if(f.getEstado().equals(s))
                    this.dadosFuncionarios.put(s, this.dadosFuncionarios.get(s) + 1);
            
    }
    
    private void atualizarNumeroDeAdmissoesNoMes(Funcionario f){
        LocalDate data_admissao = LocalDate.parse(f.getData_admissao());
        if(data_admissao.getMonth() == dataAtual.getMonth() && data_admissao.getYear() == dataAtual.getYear())
            this.dadosFuncionarios.put("f_admissoes", this.dadosFuncionarios.get("f_admissoes") + 1);
    }

    private void atualizarNumeroDeFuncionariosPorDepartamento(Funcionario f){
        String departamento = f.getNomeDepartamento();
        this.departamento_funcionario.put(departamento, this.departamento_funcionario.getOrDefault(departamento, 0) + 1);
    }
    
}
