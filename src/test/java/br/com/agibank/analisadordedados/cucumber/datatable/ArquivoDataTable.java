package br.com.agibank.analisadordedados.cucumber.datatable;

import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.PATH_DIRETORIO_RESOURCES;

public class ArquivoDataTable {

    private String nome;
    private String diretorio;
    private List<LinhaArquivoDataTable> linhas = new ArrayList<>();

    public ArquivoDataTable(){}

    public ArquivoDataTable(String nome, String diretorio) {
        this.nome = nome;
        this.diretorio = diretorio;
    }

    public String getNome() {
        return nome;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public List<LinhaArquivoDataTable> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<LinhaArquivoDataTable> linhas) {
        this.linhas = linhas;
    }

    public String getCaminhoDiretorio() {
        return PATH_DIRETORIO_RESOURCES + this.getDiretorio();
    }

    public String getCaminhoCompleto() {
        return PATH_DIRETORIO_RESOURCES + this.getDiretorio() + "/" + this.getNome();
    }
}
