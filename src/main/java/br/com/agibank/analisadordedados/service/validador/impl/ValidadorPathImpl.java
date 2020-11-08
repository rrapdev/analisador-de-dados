package br.com.agibank.analisadordedados.service.validador.impl;

import br.com.agibank.analisadordedados.service.validador.ValidadorPath;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class ValidadorPathImpl implements ValidadorPath {
    @Override
    public boolean verificarSePathCorrespondeAhUmArquivo(Path path) {
        return path.toFile().isFile();
    }
}
