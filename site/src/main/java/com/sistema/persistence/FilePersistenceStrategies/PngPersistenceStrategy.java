
package com.sistema.persistence.FilePersistenceStrategies;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.sistema.persistence.FilePersistenceStrategy;
import com.sistema.persistence.configs.FilePersistenceConfigSingleton;

public class PngPersistenceStrategy implements FilePersistenceStrategy {

    @Override
    public String salvar(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty())
            throw new IOException("Arquivo vazio ou inexistente");

        if (arquivo.getSize() > 2 * 1024 * 1024)
            throw new IOException("A imagem excede o limite de 2 MB");

        String nomeOriginal = arquivo.getOriginalFilename();
        if (nomeOriginal == null || !nomeOriginal.toLowerCase().endsWith(".png"))
            throw new IOException("Apenas imagens PNG são permitidas");

        byte[] conteudo = arquivo.getBytes();
        BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(conteudo));

        if (imagem == null)
            throw new IOException("O conteúdo não corresponde a uma imagem válida");

        if (imagem.getWidth() < 128 || imagem.getHeight() < 128)
            throw new IOException("A imagem deve ter no mínimo 128 × 128 pixels");

        FilePersistenceConfigSingleton config = FilePersistenceConfigSingleton.getInstance();
        String nomeArquivo = UUID.randomUUID() + ".png";
        File destino = new File(config.getOutLogosEmpresasAbsoluto(), nomeArquivo);

        File diretorio = destino.getParentFile();
        if (!diretorio.exists() && !diretorio.mkdirs())
            throw new IOException("Não foi possível criar o diretório de destino");

        arquivo.transferTo(destino);

        return config.getOutLogosEmpresasRelativo() + "/" + nomeArquivo;
    }
}
