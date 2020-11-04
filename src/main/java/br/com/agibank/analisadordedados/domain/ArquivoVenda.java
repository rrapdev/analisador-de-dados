package br.com.agibank.analisadordedados.domain;

import lombok.Data;

import java.nio.file.Path;

@Data
public class ArquivoVenda {

    private String nome;
    private Path path;
    private RelatorioResumoVendas relatorioResumoVendas;

    public ArquivoVenda(Path path) {
        this.nome = path.getFileName().toString();
        this.path = path;
    }

    public String getCaminhoCompletoArquivoSaida() {
        String nomeSaida = this.getNome().replace(EXTENSAO_VALIDA_DAT, EXTENSAO_SAIDA_DONE_DAT);
        return DIRETORIO_SAIDA + SEPARADOR + nomeSaida;
    }

    public static final String DIRETORIO_ENTRADA = "src/main/resources/data/in";
    public static final String DIRETORIO_SAIDA = "src/main/resources/data/out";
    public static final String EXTENSAO_VALIDA_DAT = ".dat";
    public static final String EXTENSAO_SAIDA_DONE_DAT = ".done.dat";
    public static final String SEPARADOR = "/";
}
