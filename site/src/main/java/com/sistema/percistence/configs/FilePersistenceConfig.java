package com.sistema.percistence.configs;

import java.io.File;

public class FilePersistenceConfig {
    
    private static FilePersistenceConfig conf;
    //locais de salvamento
    private String out = System.getProperty("user.dir").replaceAll("\\\\", "/") + "./uploads";
    private String outDocumentosSolicitacao = out + "/documentosSolicitacao";
    //validação de arquivos
    private String[] extencoesPermitidas = {
        "pdf", "png", "jpeg", "jpg"
    };


    private FilePersistenceConfig(){}

    public static FilePersistenceConfig getInstance(){
        if(conf == null)
            conf = new FilePersistenceConfig();
        return conf;
    }

    public String getOutDocumentosSolicitacao() {
        return outDocumentosSolicitacao;
    }

    public boolean isValido(String nomeArquivo){
        String[] arquivoPartes = nomeArquivo.split("\\.");
        String arquivoExtencao = arquivoPartes[arquivoPartes.length - 1];

        for(String extencaoPermitida : extencoesPermitidas)
            if( arquivoExtencao.equals(extencaoPermitida) )
                return true;

        return false;
    }

    public void configurarDiretorios(){
        File[] files = {new File(out), new File(outDocumentosSolicitacao)};
        for(File file : files)
            if(!file.exists())
                file.mkdir();
    }
    

}
