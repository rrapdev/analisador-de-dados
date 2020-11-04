package br.com.agibank.analisadordedados.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.DADOS_VENDA;
import static br.com.agibank.analisadordedados.util.ConstantesUtil.DADOS_VENDA_TOTAL_1500;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class VendaTest {

    private Venda venda;

    @Before
    public void inicializarContexto() {
        venda = new Venda();
    }

    @Test
    public void aoExecutarMetodoImportarDadosDeveriaAtribuirDados() {
        venda.importarDados(DADOS_VENDA);
        ItemVenda itemVenda = venda.getItensVenda().get(0);
        assertEquals(Integer.valueOf(DADOS_VENDA[1]), venda.getId());
        assertEquals(DADOS_VENDA[3], venda.getNomeVendedor());
        assertEquals(Integer.valueOf(1), itemVenda.getId());
        assertEquals(Integer.valueOf(10), itemVenda.getQuantidade());
        assertEquals(new BigDecimal("100"), itemVenda.getPreco());
    }

    @Test
    public void aoExecutarGetTotalVendaDeveriaRetornarSomaDeTodosOsItensDaVenda() {
        venda.importarDados(DADOS_VENDA_TOTAL_1500);
        assertEquals(new BigDecimal("1500.00"), venda.getTotalVenda());
    }

    @Test
    public void aoExecutarMetodoAdicionarAoRelatorioDeveriaDeveriaRetornarRelatorioComVendaAdicionado() {
        RelatorioResumoVendas relatorio = new RelatorioResumoVendas();
        venda.adicionarAoRelatorio(relatorio);
        Venda retorno = relatorio.getVendas().get(0);
        assertSame(venda, retorno);
    }

    @Test
    public void aoSetarValoresDeveriaAtribuirAsVariaveis() {
        Venda venda = new Venda();
        String nomeVendedor = "nome";
        Integer id = 1;
        List<ItemVenda> itens = new ArrayList<>();
        venda.setNomeVendedor(nomeVendedor);
        venda.setId(id);
        venda.setItensVenda(itens);
        assertSame(id, venda.getId());
        assertSame(nomeVendedor, venda.getNomeVendedor());
        assertSame(itens, venda.getItensVenda());
    }

}
