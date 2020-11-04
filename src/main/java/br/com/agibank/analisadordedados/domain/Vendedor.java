package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Vendedor extends TipoDado {
    private String cpf;
    private String nome;
    private BigDecimal salario;

    @Override
    public void importarDados(String[] dados) {
        this.cpf = dados[1];
        this.nome = dados[2];
        this.salario = new BigDecimal(dados[3]);
    }

    @Override
    public RelatorioResumoVendas adicionarAoRelatorio(RelatorioResumoVendas relatorio) {
        relatorio.adicionarVendedor(this);
        return relatorio;
    }
}
