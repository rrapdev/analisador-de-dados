package br.com.agibank.analisadordedados.service.component;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ManipuladorArquivos {

    Stream<Path> listarPathsDoDiretorio(String caminhoDiretorio);

    void criarArquivoContendoResumoNoDiretorioPadraoSaida(Resumo resumo, String caminhoCompletoArquivoSaida);

    void apagarArquivo(Arquivo arquivo);
}