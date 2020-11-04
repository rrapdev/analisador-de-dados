package br.com.agibank.analisadordedados.builder;

import br.com.agibank.analisadordedados.domain.ItemVenda;

import java.math.BigDecimal;

public class ItemVendaBuilder {

    private ItemVenda itemVenda = new ItemVenda();

    public static ItemVendaBuilder umItemVenda() {
        return new ItemVendaBuilder();
    }

    public static ItemVendaBuilder umItemVendaValido() {
        return umItemVenda().comId(Integer.valueOf(1)).comQuantidade(Integer.valueOf(2)).comPreco(new BigDecimal("10.50"));
    }

    public ItemVendaBuilder comPreco(BigDecimal preco) {
        itemVenda.setPreco(preco);
        return this;
    }

    public ItemVendaBuilder comId(Integer id) {
        itemVenda.setId(id);
        return this;
    }
    public ItemVendaBuilder comQuantidade(Integer quantidade) {
        itemVenda.setQuantidade(quantidade);
        return this;
    }

    public ItemVenda build() {
        return itemVenda;
    }
}
