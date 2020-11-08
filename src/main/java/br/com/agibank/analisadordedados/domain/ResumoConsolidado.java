package br.com.agibank.analisadordedados.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ResumoConsolidado implements Resumo {

    @Getter @Setter
    private List<Cliente> clientes = new ArrayList<>();

    @Getter @Setter
    private List<Vendedor> vendedores = new ArrayList<>();

    @Getter
    private List<Venda> vendas = new ArrayList<>();

    @Getter @Setter
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

    @Override
    public String gerar() {
        String retorno = String.format("Clientes: %s;", this.getQuantidadeClientes()) + System.lineSeparator();
        retorno += String.format("Vendedores: %s;", this.getQuantidadeVendedores()) + System.lineSeparator();
        retorno += String.format("ID da Venda mais cara: %s;", this.getIdVendaMaisCara()) + System.lineSeparator();
        retorno += String.format("O pior vendedor: %s;", this.getNomePiorVendedor());
        return retorno;
    }
}