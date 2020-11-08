package br.com.agibank.analisadordedados.service.component;

import br.com.agibank.analisadordedados.domain.Arquivo;

import java.nio.file.Path;

public interface GeradorEntidadeArquivo {

    Arquivo criar(Path path);

}
