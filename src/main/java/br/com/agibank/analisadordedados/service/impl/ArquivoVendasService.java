package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.ArquivoVenda;
import br.com.agibank.analisadordedados.domain.RelatorioResumoVendas;
import br.com.agibank.analisadordedados.domain.TipoDado;
import br.com.agibank.analisadordedados.service.ArquivoService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArquivoVendasService implements ArquivoService {

    @Override
    public List<String> processar() {
        List<ArquivoVenda> arquivoVendas = lerArquivosDeVendaEmDiretorioPadrao();

        arquivoVendas = analisarArquivosGerandoRelatorio(arquivoVendas);

        gerarArquivosConsolidadosEmDiretorioPadrao(arquivoVendas);

        return obterNomesArquivosProcessados(arquivoVendas);
    }

    private List<ArquivoVenda> analisarArquivosGerandoRelatorio(List<ArquivoVenda> arquivoVendas) {
        for (ArquivoVenda arquivoVenda : arquivoVendas) {
            RelatorioResumoVendas relatorioResumoVendas = this.gerarRelatorioResumo(arquivoVenda);
            arquivoVenda.setRelatorioResumoVendas(relatorioResumoVendas);
        }

        return arquivoVendas;
    }

    private RelatorioResumoVendas gerarRelatorioResumo(ArquivoVenda arquivoVenda) {
        RelatorioResumoVendas relatorio = new RelatorioResumoVendas();

        try {
            Stream<String> linhas = Files.lines(arquivoVenda.getPath());

            linhas.map(linha -> linha.split("ç"))
                    .collect(Collectors.toList())
                    .forEach(linha -> {
                        TipoDado.descobrir(linha).adicionarAoRelatorio(relatorio);
                    });


        } catch (IOException e) {
            throw new RuntimeException("Falha ao processar arquivo, linhas inválidas!");
        }

        return relatorio;
    }

    private void gerarArquivosConsolidadosEmDiretorioPadrao(List<ArquivoVenda> listaArquivoVenda) {
        criarDiretorioSeNaoExistir(ArquivoVenda.DIRETORIO_SAIDA);
        listaArquivoVenda.forEach(arquivoVenda -> {
            try {
                FileWriter fileWriter = new FileWriter(arquivoVenda.getCaminhoCompletoArquivoSaida());

                fileWriter.write(String.format("Clientes: %s;", arquivoVenda.getRelatorioResumoVendas().getQuantidadeClientes()) + System.lineSeparator());
                fileWriter.write(String.format("Vendedores: %s;", arquivoVenda.getRelatorioResumoVendas().getQuantidadeVendedores()) + System.lineSeparator());
                fileWriter.write(String.format("ID da Venda mais cara: %s;", arquivoVenda.getRelatorioResumoVendas().getIdVendaMaisCara()) + System.lineSeparator());
                fileWriter.write(String.format("O pior vendedor: %s;", arquivoVenda.getRelatorioResumoVendas().getNomePiorVendedor()));

                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void criarDiretorioSeNaoExistir(String caminhoDiretorio) {
        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    private List<ArquivoVenda> lerArquivosDeVendaEmDiretorioPadrao() {
        List<ArquivoVenda> listaArquivos = new ArrayList<>();
        for (Path path : obterListaPathsDeDiretorio(ArquivoVenda.DIRETORIO_ENTRADA)) {
            ArquivoVenda arquivoVenda = new ArquivoVenda(path);
            listaArquivos.add(arquivoVenda);
        }
        return listaArquivos;
    }

    private List<Path> obterListaPathsDeDiretorio(String diretorio) {
        try {
            return Files.list(Paths.get(diretorio))
                    .filter(path -> path.toFile().getName().endsWith(ArquivoVenda.EXTENSAO_VALIDA_DAT) && path.toFile().isFile())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Falha ao listar arquivos do diretório: " + diretorio);
        }
    }

    private List<String> obterNomesArquivosProcessados(List<ArquivoVenda> arquivoVendas) {
        List<String> listaNomes = new ArrayList<>();
        arquivoVendas.forEach( arquivo -> listaNomes.add(arquivo.getNome()));
        return listaNomes;
    }

}