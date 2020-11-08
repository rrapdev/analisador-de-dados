package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.service.ArquivoService;
import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.service.AnalisadorDadosService;
import br.com.agibank.analisadordedados.service.ProcessadorArquivos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class ProcessadorArquivosImpl implements ProcessadorArquivos {

    ArquivoService arquivoService;
    AnalisadorDadosService analisadorDadosService;

    @Autowired
    public ProcessadorArquivosImpl(ArquivoService arquivoService,
                                   AnalisadorDadosService analisadorDadosService) {
        this.arquivoService = arquivoService;
        this.analisadorDadosService = analisadorDadosService;
    }

    @Override
    public void processar() {
        arquivoService.lerArquivos().forEach(this::processarArquivo);
    }

    private void processarArquivo(Arquivo arquivo) {
        arquivo.validar();
        Resumo resumo = analisadorDadosService.analisarConteudo(arquivo);
        arquivoService.gerarArquivoConsolidado(resumo, arquivo);
    }
}