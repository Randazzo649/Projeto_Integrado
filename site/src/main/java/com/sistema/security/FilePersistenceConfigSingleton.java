package com.sistema.security;

import java.io.File;

public class FilePersistenceConfigSingleton {
    
    private static FilePersistenceConfigSingleton conf;
    //locais de salvamento
    private String outAbsoluto = System.getProperty("user.dir").replaceAll("\\\\", "/") + "./uploads";
    private String outRelativo = "/uploads";
    //docs
    private String outDocumentosSolicitacaoAbsoluto =  outAbsoluto + "/documentosSolicitacao";
    private String outDocumentosSolicitacaoRelativo = outRelativo + "/documentosSolicitacao";
    //logos
    private String outLogosEmpresasAbsoluto = outAbsoluto + "/logosEmpresas";
    private String outLogosEmpresasRelativo = outRelativo + "/logosEmpresas";
    //validação de arquivos
    private String[] extencoesPermitidas = {
        "pdf", "png", "jpeg", "jpg"
    };


    private FilePersistenceConfigSingleton(){}

    public static FilePersistenceConfigSingleton getInstance(){
        if(conf == null)
            conf = new FilePersistenceConfigSingleton();
        return conf;
    }

    //docs
    public String getOutDocumentosSolicitacaoAbsoluto() {
        return outDocumentosSolicitacaoAbsoluto;
    }

    public String getOutDocumentosSolicitacaoRelativo() {
        return outDocumentosSolicitacaoRelativo;
    }

    //logos
    public String getOutLogosEmpresasAbsoluto() {
        return outLogosEmpresasAbsoluto;
    }

    public String getOutLogosEmpresasRelativo() {
        return outLogosEmpresasRelativo;
    }

    public boolean isValido(String caminho){

        String[] arquivoPartes = caminho.split("\\.");
        if (arquivoPartes.length < 2)
            return false;

        String arquivoExtencao = arquivoPartes[arquivoPartes.length - 1];
        for (String extencaoPermitida : extencoesPermitidas)
            if (arquivoExtencao.equalsIgnoreCase(extencaoPermitida))
                return true;

        return false;
    }

    public void configurarDiretorios(){
        File[] files = {new File(outAbsoluto), new File(outDocumentosSolicitacaoAbsoluto), new File(outLogosEmpresasAbsoluto)};
        for(File file : files)
            if(!file.exists())
                file.mkdir();
    }
    

}
