package com.sistema.persistence;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FilePersistenceStrategy {
    //retorna o caminho do arquivo salvo
    String salvar(MultipartFile arquivo) throws IOException;
}
