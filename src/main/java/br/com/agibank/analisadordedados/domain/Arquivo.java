package br.com.agibank.analisadordedados.domain;

import java.nio.file.Path;

public interface Arquivo {

    void validar();

    void setPath(Path path);

    Path getPath();

    String obterConteudoDoArquivo();

    String obterNomeDoArquivoConsolidado();

}
