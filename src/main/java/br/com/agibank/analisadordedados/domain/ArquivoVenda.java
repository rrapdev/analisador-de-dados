package br.com.agibank.analisadordedados.domain;

import br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException;
import br.com.agibank.analisadordedados.exception.NegocioException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException.MENSAGEM_FALHA_AO_LER_ARQUIVO;

public class ArquivoVenda implements Arquivo {

    private Path path;

    @Override
    public void validar() {
        if (!getPath().toFile().getName().endsWith(".dat")) {
            throw new NegocioException("O arquivo a ser processado não possui extensão válida .dat!");
        }
    }

    @Override
    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public Path getPath() {
        return this.path;
    }

    @Override
    public String obterConteudoDoArquivo() {
        try {
            return Files.readString(getPath());
        } catch (IOException e) {
            throw new FalhaAoAcessarDiretorioArquivoException(MENSAGEM_FALHA_AO_LER_ARQUIVO);
        }
    }

    @Override
    public String obterNomeDoArquivoConsolidado() {
        return getPath().toFile().getName().replace(".dat", ".done.dat");
    }
}