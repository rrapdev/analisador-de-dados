package br.com.agibank.analisadordedados.service.component.impl;

import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException;
import br.com.agibank.analisadordedados.service.component.ManipuladorArquivos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException.MENSAGEM_FALHA_AO_CRIAR_ARQUIVO;
import static br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException.MENSAGEM_FALHA_AO_LER_DIRETORIO;

@Component
public class ManipuladorArquivosImpl implements ManipuladorArquivos {

    private static final Logger log = LoggerFactory.getLogger(ManipuladorArquivosImpl.class);

    @Override
    public Stream<Path> listarPathsDoDiretorio(String caminhoDiretorio) {
        try {
            return Files.list(Path.of(caminhoDiretorio));
        } catch (IOException e) {
            throw new FalhaAoAcessarDiretorioArquivoException(MENSAGEM_FALHA_AO_LER_DIRETORIO);
        }
    }

    @Override
    public void criarArquivoContendoResumoNoDiretorioPadraoSaida(Resumo resumo, String caminhoArquivoSaidaComNome) {
        try {
            Path file = Files.createFile(Path.of(caminhoArquivoSaidaComNome));
            Files.writeString(file, resumo.gerar());
        } catch (IOException e) {
            throw new FalhaAoAcessarDiretorioArquivoException(MENSAGEM_FALHA_AO_CRIAR_ARQUIVO + caminhoArquivoSaidaComNome);
        }
    }
}