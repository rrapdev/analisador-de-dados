package br.com.agibank.analisadordedados.util;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.LinhaArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.ResumoDataTable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.PATH_DIRETORIO_RESOURCES;

@Component
public class ManipuladorArquivoTestHelper {
    public void criarArquivo(ArquivoDataTable arquivoAhSerCriado) {
        criarDiretorioSeNaoExistir(arquivoAhSerCriado.getCaminhoDiretorio());
        criarArquivoTratandoPossivelExcecao(arquivoAhSerCriado);
    }

    public Boolean verificarSeArquivoExiste(ArquivoDataTable arquivoDataTable) {
        File arquivo = new File(arquivoDataTable.getCaminhoCompleto());
        return arquivo.exists();
    }

    @Deprecated
    private void criarDiretorioSeNaoExistir(String caminhoDiretorio) {
        File diretorio = new File(caminhoDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    private void criarArquivoTratandoPossivelExcecao(ArquivoDataTable arquivo) {
        try {
            FileWriter fileWriter = new FileWriter(arquivo.getCaminhoCompleto());
            int cont = 1;
            if (!arquivo.getLinhas().isEmpty()) {
                for (LinhaArquivoDataTable linha : arquivo.getLinhas()) {
                    String linhaAhSerEscrita = cont++ == 1? linha.getDados() : System.lineSeparator() + linha.getDados();
                    fileWriter.write(linhaAhSerEscrita);
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("O manipulador de arquivo não conseguiu criar o arquivo. " +
                    "Verifique permissões na pasta solicitada: "
                    + arquivo.getCaminhoCompleto());
        }
    }

    public void deletarArquivosEmDiretorio(String diretorio) {
        List<Path> listaArquivos = new ArrayList<>();
        try {
            listaArquivos = listarArquivosDoDiretorio(PATH_DIRETORIO_RESOURCES + diretorio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaArquivos.forEach( arquivo -> {
            boolean isDeleted = arquivo.toFile().delete();
            if (!isDeleted) throw new RuntimeException("Não conseguiu apagar o arquivo para limpeza de contexto. " +
                    "Verifique permissões no diretório: " + PATH_DIRETORIO_RESOURCES + diretorio);
        });
    }

    public boolean verificarSeExisteAlgumArquivoEmDiretorioSaida(String diretorio) {
        List<Path> listaArquivos = listarArquivosDoDiretorio(PATH_DIRETORIO_RESOURCES + diretorio);
        return !listaArquivos.isEmpty();
    }

    private List<Path> listarArquivosDoDiretorio(String diretorio) {
        try {
            return Files.list(Paths.get(diretorio))
                    .filter(path -> path.toFile().isFile())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Não encontrou arquivos para serem listados!");
    }

    public Stream<String> lerArquivo(ArquivoDataTable arquivoDataTable) {
        try {
            List<Path> listaArquivos = listarArquivosDoDiretorio(arquivoDataTable.getCaminhoDiretorio());
            Stream<String> linhas = Files.lines(listaArquivos.get(0));
            return linhas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Não encontrou arquivo esperado para ser lido!");
    }

    public ResumoDataTable lerArquivoRetornandoDataTable(ArquivoDataTable arquivoDataTable) {
        List<String> listaLinhas = lerArquivo(arquivoDataTable).collect(Collectors.toList());
        Map<String, String> resumoMap = new HashMap<>();

        for (int i = 0; i < listaLinhas.size(); i++) {
            String linha = listaLinhas.get(i);
            String nomeCampo = linha.split(":")[0];
            int begin = linha.indexOf(":");
            int end = linha.indexOf(";");
            String valorCampo = linha.substring(begin+1, end).trim();
            resumoMap.put(nomeCampo, valorCampo);
        }

        ResumoDataTable resumoArquivo = new ResumoDataTable();
        resumoArquivo.setQuantidadeClientes(Integer.valueOf(resumoMap.get("Clientes")));
        resumoArquivo.setQuantidadeVendedores(Integer.valueOf(resumoMap.get("Vendedores")));
        resumoArquivo.setIdVendaMaisCara(Integer.valueOf(resumoMap.get("ID da Venda mais cara")));
        resumoArquivo.setNomePiorVendedor(resumoMap.get("O pior vendedor"));

        return resumoArquivo;
    }
}