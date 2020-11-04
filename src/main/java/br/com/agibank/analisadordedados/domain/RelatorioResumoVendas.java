package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class RelatorioResumoVendas {

    private List<Cliente> clientes = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Venda> vendas = new ArrayList<>();

    private Venda vendaMaisCara = new Venda();

    public Integer getQuantidadeClientes() {
        return clientes.size();
    }

    public Integer getQuantidadeVendedores() {
        return vendedores.size();
    }

    public Integer getIdVendaMaisCara() {
        return vendaMaisCara.getId();
    }

    public String getNomePiorVendedor() {
        return vendas.stream()
                .collect(Collectors.groupingBy(Venda::getNomeVendedor,
                        Collectors.reducing(BigDecimal.ZERO, Venda::getTotalVenda, BigDecimal::add)))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList())
                .get(0).getKey();
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
        atualizarSeForVendaMaisCara(venda);
    }

    private void atualizarSeForVendaMaisCara(Venda venda) {
        if (vendaMaisCara == null || venda.getTotalVenda().compareTo(vendaMaisCara.getTotalVenda()) > 0) {
            this.vendaMaisCara = venda;
        }
    }
}