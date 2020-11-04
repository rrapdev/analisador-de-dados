package br.com.agibank.analisadordedados.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ItemVendaTest {

    private ItemVenda itemVenda;

    @Test
    public void aoChamarConstrutorPassandoDadosDeveriaAtribuirParaIdQuantidadePreco() {
        String[] dados = {String.valueOf(ID_ITEM_1), String.valueOf(QUANTIDADE_10), String.valueOf(PRECO_100_00)};
        itemVenda = new ItemVenda(dados);
        assertEquals(ID_ITEM_1, itemVenda.getId());
        assertEquals(QUANTIDADE_10, itemVenda.getQuantidade());
        assertEquals(PRECO_100_00, itemVenda.getPreco());
    }

    @Test
    public void aoCalcularTotalDeveriaRetornarMultiplicacaoEntreQuantidadeEhPreco() {
        itemVenda = new ItemVenda();
        itemVenda.setPreco(PRECO_100_00);
        itemVenda.setQuantidade(QUANTIDADE_10);
        assertEquals(VALOR_TOTAL_1000, itemVenda.calcularTotal());
    }

    public static final Integer ID_ITEM_1 = 1;
    public static final Integer QUANTIDADE_10 = 10;
    public static final BigDecimal PRECO_100_00 = new BigDecimal("100.00");
    public static final BigDecimal VALOR_TOTAL_1000 = new BigDecimal("1000.00");
}
