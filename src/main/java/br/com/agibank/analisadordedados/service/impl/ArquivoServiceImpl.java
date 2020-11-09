package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.service.ArquivoService;
import br.com.agibank.analisadordedados.service.component.GeradorEntidadeArquivo;
import br.com.agibank.analisadordedados.service.component.ManipuladorArquivos;
import br.com.agibank.analisadordedados.service.validador.ValidadorPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ArquivoServiceImpl implements ArquivoService {

    ManipuladorArquivos manipuladorArquivos;
    ValidadorPath validadorPath;
    GeradorEntidadeArquivo geradorEntidadeArquivo;

    @Autowired
    public ArquivoServiceImpl(ManipuladorArquivos manipuladorArquivos,
                              ValidadorPath validadorPath,
                              GeradorEntidadeArquivo geradorEntidadeArquivo) {
        this.manipuladorArquivos = manipuladorArquivos;
        this.validadorPath = validadorPath;
        this.geradorEntidadeArquivo = geradorEntidadeArquivo;
    }

    @Override
    public List<Arquivo> lerArquivos() {
        return manipuladorArquivos.listarPathsDoDiretorio(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)
                .filter(path -> validadorPath.verificarSePathCorrespondeAhUmArquivo(path))
                .map(path -> geradorEntidadeArquivo.criar(path))
                .collect(Collectors.toList());
    }

    @Override
    public void gerarArquivoConsolidado(Resumo resumo, Arquivo arquivo) {
        manipuladorArquivos.criarArquivoContendoResumoNoDiretorioPadraoSaida(resumo,
                CAMINHO_DIRETORIO_PADRAO_SAIDA_DADOS + arquivo.obterNomeDoArquivoConsolidado());
    }

    @Override
    public void apagarArquivoProcessado(Arquivo arquivo) {
        manipuladorArquivos.apagarArquivo(arquivo);
    }

    public static final String CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS = "src/main/resources/data/in";
    public static final String CAMINHO_DIRETORIO_PADRAO_SAIDA_DADOS = "src/main/resources/data/out/";
}