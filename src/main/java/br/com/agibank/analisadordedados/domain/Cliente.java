package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cliente implements TipoDado {
    private String cnpj;
    private String nome;
    private String areaNegocio;

    @Override
    public void importarDados(String[] dados) {
        this.cnpj = dados[1];
        this.nome = dados[2];
        this.areaNegocio = dados[3];
    }

    @Override
    public ResumoConsolidado adicionarAoRelatorio(ResumoConsolidado relatorio) {
        relatorio.adicionarCliente(this);
        return relatorio;
    }
}