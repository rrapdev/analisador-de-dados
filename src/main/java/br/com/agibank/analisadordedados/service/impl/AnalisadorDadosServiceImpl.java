package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.domain.ResumoConsolidado;
import br.com.agibank.analisadordedados.service.AnalisadorDadosService;
import br.com.agibank.analisadordedados.domain.TipoDadoFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class AnalisadorDadosServiceImpl implements AnalisadorDadosService {

    @Override
    public Resumo analisarConteudo(Arquivo arquivo) {
        ResumoConsolidado resumoConsolidado = new ResumoConsolidado();

        Arrays.stream(arquivo.obterConteudoDoArquivo().split("\n"))
                .map(linha-> linha.split(SEPARADOR_ITEM_C))
                .collect(Collectors.toList())
                .forEach(linha-> TipoDadoFactory.criar(linha).adicionarAoRelatorio(resumoConsolidado));

        return resumoConsolidado;
    }

    public static final String SEPARADOR_ITEM_C = "ç";
}