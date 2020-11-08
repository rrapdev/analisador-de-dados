package br.com.agibank.analisadordedados.service.component.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.ArquivoVenda;
import br.com.agibank.analisadordedados.service.component.GeradorEntidadeArquivo;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class GeradorEntidadeArquivoImpl implements GeradorEntidadeArquivo {
    @Override
    public Arquivo criar(Path path) {
        Arquivo arquivo = new ArquivoVenda();
        arquivo.setPath(path);
        return arquivo;
    }
}