package br.com.agibank.analisadordedados.domain;

import br.com.agibank.analisadordedados.builder.ItemVendaBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class RelatorioResumoVendasTest {

    private RelatorioResumoVendas relatorioResumoVendas;

    @Test
    public void aoExecutarGetQuantidadeClientesDeveriaRetornarResultadoEsperado() {
        relatorioResumoVendas = new RelatorioResumoVendas();
        relatorioResumoVendas.adicionarCliente(new Cliente());

        assertEquals(Integer.valueOf(1), relatorioResumoVendas.getQuantidadeClientes());
        relatorioResumoVendas.adicionarCliente(new Cliente());
        assertEquals(Integer.valueOf(2), relatorioResumoVendas.getQuantidadeClientes());
    }

    @Test
    public void aoExecutarGetQuantidadeVendedoresDeveriaRetornarResultadoEsperado() {
        relatorioResumoVendas = new RelatorioResumoVendas();
        relatorioResumoVendas.adicionarVendedor(new Vendedor());

        assertEquals(Integer.valueOf(1), relatorioResumoVendas.getQuantidadeVendedores());
        relatorioResumoVendas.adicionarVendedor(new Vendedor());
        assertEquals(Integer.valueOf(2), relatorioResumoVendas.getQuantidadeVendedores());
    }

    @Test
    public void aoExecutarGetIdVendaMaisCaraDeveriaRetornarResultadoEsperado() {
        Venda vendaMaisBarata = obterVendaComQuantidadePreco(11,1, new BigDecimal("5"), "");
        Venda vendaMaisCara = obterVendaComQuantidadePreco(22, 1, new BigDecimal("20"), "");
        Venda vendaIntermediaria = obterVendaComQuantidadePreco(33, 3, new BigDecimal("2.50"), "");
        relatorioResumoVendas = new RelatorioResumoVendas();
        relatorioResumoVendas.adicionarVenda(vendaMaisBarata);
        relatorioResumoVendas.adicionarVenda(vendaMaisCara);
        relatorioResumoVendas.adicionarVenda(vendaIntermediaria);

        assertEquals(Integer.valueOf(22), relatorioResumoVendas.getIdVendaMaisCara());
    }

    @Test
    public void aoExecutarGetNomePiorVendedorDeveriaRetornar() {
        Venda vendaMaisBarata = obterVendaComQuantidadePreco(11,1, new BigDecimal("5"), "Maria");
        Venda vendaMaisCara = obterVendaComQuantidadePreco(22, 1, new BigDecimal("20"), "Marlene");
        Venda vendaIntermediaria = obterVendaComQuantidadePreco(33, 3, new BigDecimal("2.50"), "Julia");
        relatorioResumoVendas = new RelatorioResumoVendas();
        relatorioResumoVendas.adicionarVenda(vendaMaisBarata);
        relatorioResumoVendas.adicionarVenda(vendaMaisCara);
        relatorioResumoVendas.adicionarVenda(vendaIntermediaria);

        assertEquals("Maria", relatorioResumoVendas.getNomePiorVendedor());
    }

    @Test
    public void aoSetarAtributosDeveriaRetornarResultadoEsperado() {
        List<Cliente> clientes = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        List<Vendedor> vendedores = new ArrayList<>();
        Venda venda = new Venda();
        RelatorioResumoVendas relatorio = new RelatorioResumoVendas();
        relatorio.setClientes(clientes);
        relatorio.setVendaMaisCara(venda);
        relatorio.setVendas(vendas);
        relatorio.setVendedores(vendedores);

        assertSame(clientes, relatorio.getClientes());
        assertSame(venda, relatorio.getVendaMaisCara());
        assertSame(vendas, relatorio.getVendas());
        assertSame(vendedores, relatorio.getVendedores());
    }

    private Venda obterVendaComQuantidadePreco(Integer idVenda, Integer quantidade, BigDecimal preco, String nomeVendedor) {
        Venda venda = new Venda();
        venda.setId(idVenda);
        venda.setNomeVendedor(nomeVendedor);
        List<ItemVenda> itensVenda = new ArrayList<>();
        itensVenda.add(ItemVendaBuilder.umItemVendaValido().comQuantidade(quantidade).comPreco(preco).build());
        venda.setItensVenda(itensVenda);
        return venda;
    }
}
