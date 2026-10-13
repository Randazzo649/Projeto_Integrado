
package com.sistema.persistence.FilePersistenceStrategies;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.sistema.persistence.FilePersistenceStrategy;
import com.sistema.persistence.configs.FilePersistenceConfigSingleton;

public class PdfPersistenceStrategy implements FilePersistenceStrategy {

    @Override
    public String salvar(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty())
            throw new IOException("Arquivo vazio ou inexistente");

        if (arquivo.getSize() > 10 * 1024 * 1024)
            throw new IOException("O PDF excede o limite de 10 MB");

        String nomeOriginal = arquivo.getOriginalFilename();
        if (nomeOriginal == null || !nomeOriginal.toLowerCase().endsWith(".pdf"))
            throw new IOException("Apenas arquivos PDF são permitidos");

        byte[] conteudo = arquivo.getBytes();
        if (conteudo.length < 5 ||
            conteudo[0] != '%' || conteudo[1] != 'P' ||
            conteudo[2] != 'D' || conteudo[3] != 'F' ||
            conteudo[4] != '-')
            throw new IOException("O conteúdo não corresponde a um PDF");

        FilePersistenceConfigSingleton config = FilePersistenceConfigSingleton.getInstance();
        String nomeArquivo = UUID.randomUUID() + ".pdf";
        File destino = new File(config.getOutDocumentosSolicitacaoAbsoluto(), nomeArquivo);

        File diretorio = destino.getParentFile();
        if (!diretorio.exists() && !diretorio.mkdirs())
            throw new IOException("Não foi possível criar o diretório de destino");

        arquivo.transferTo(destino);

        return config.getOutDocumentosSolicitacaoRelativo() + "/" + nomeArquivo;
    }
}
