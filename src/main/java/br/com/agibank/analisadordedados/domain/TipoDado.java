package br.com.agibank.analisadordedados.domain;

public interface TipoDado {
    void importarDados(String[] dados);
    ResumoConsolidado adicionarAoRelatorio(ResumoConsolidado relatorio);
}
