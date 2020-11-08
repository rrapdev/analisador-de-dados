package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Venda implements TipoDado {
    private Integer id;
    private String nomeVendedor;
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @Override
    public void importarDados(String[] dados) {
        this.id = Integer.valueOf(dados[1]);
        this.nomeVendedor = dados[3];

        String[] dadosProdutos = extrairDadosProduto(dados[2]);
        this.itensVenda = gerarListaProdutos(dadosProdutos);
    }

    public BigDecimal getTotalVenda() {
        BigDecimal total = new BigDecimal("0.00");
        for (ItemVenda itenVenda : itensVenda) {
            total = total.add(itenVenda.calcularTotal());
        }
        return total;
    }

    private List<ItemVenda> gerarListaProdutos(String[] dadosProdutos) {
        List<ItemVenda> listaProdutos = new ArrayList<>();
        Arrays.stream(dadosProdutos).forEach(produto -> listaProdutos.add(new ItemVenda(produto.split("-"))));
        return listaProdutos;
    }

    private String[] extrairDadosProduto(String dados) {
        return dados.replace("[", "")
                .replace("]", "")
                .split(",");
    }

    @Override
    public ResumoConsolidado adicionarAoRelatorio(ResumoConsolidado relatorio) {
        relatorio.adicionarVenda(this);
        return relatorio;
    }
}
