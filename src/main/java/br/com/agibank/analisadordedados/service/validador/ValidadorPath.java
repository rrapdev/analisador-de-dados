package br.com.agibank.analisadordedados.service.validador;

import java.nio.file.Path;

public interface ValidadorPath {

    boolean verificarSePathCorrespondeAhUmArquivo(Path path);

}
