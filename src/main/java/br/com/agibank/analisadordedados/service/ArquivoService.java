package br.com.agibank.analisadordedados.service;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;

import java.util.List;

public interface ArquivoService {

    List<Arquivo> lerArquivos();

    void gerarArquivoConsolidado(Resumo resumo, Arquivo arquivo);

    void apagarArquivoProcessado(Arquivo arquivo);
}