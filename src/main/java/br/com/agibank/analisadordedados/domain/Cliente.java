package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cliente extends TipoDado {
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
    public RelatorioResumoVendas adicionarAoRelatorio(RelatorioResumoVendas relatorio) {
        relatorio.adicionarCliente(this);
        return relatorio;
    }
}