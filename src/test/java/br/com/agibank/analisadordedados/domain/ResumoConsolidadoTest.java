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
public class ResumoConsolidadoTest {

    private ResumoConsolidado resumoConsolidado;

    @Test
    public void aoExecutarGetQuantidadeClientesDeveriaRetornarResultadoEsperado() {
        resumoConsolidado = new ResumoConsolidado();
        resumoConsolidado.adicionarCliente(new Cliente());

        assertEquals(Integer.valueOf(1), resumoConsolidado.getQuantidadeClientes());
        resumoConsolidado.adicionarCliente(new Cliente());
        assertEquals(Integer.valueOf(2), resumoConsolidado.getQuantidadeClientes());
    }

    @Test
    public void aoExecutarGetQuantidadeVendedoresDeveriaRetornarResultadoEsperado() {
        resumoConsolidado = new ResumoConsolidado();
        resumoConsolidado.adicionarVendedor(new Vendedor());

        assertEquals(Integer.valueOf(1), resumoConsolidado.getQuantidadeVendedores());
        resumoConsolidado.adicionarVendedor(new Vendedor());
        assertEquals(Integer.valueOf(2), resumoConsolidado.getQuantidadeVendedores());
    }

    @Test
    public void aoExecutarGetIdVendaMaisCaraDeveriaRetornarResultadoEsperado() {
        Venda vendaMaisBarata = obterVendaComQuantidadePreco(11,1, new BigDecimal("5"), "");
        Venda vendaMaisCara = obterVendaComQuantidadePreco(22, 1, new BigDecimal("20"), "");
        Venda vendaIntermediaria = obterVendaComQuantidadePreco(33, 3, new BigDecimal("2.50"), "");
        resumoConsolidado = new ResumoConsolidado();
        resumoConsolidado.adicionarVenda(vendaMaisBarata);
        resumoConsolidado.adicionarVenda(vendaMaisCara);
        resumoConsolidado.adicionarVenda(vendaIntermediaria);

        assertEquals(Integer.valueOf(22), resumoConsolidado.getIdVendaMaisCara());
    }

    @Test
    public void aoExecutarGetNomePiorVendedorDeveriaRetornar() {
        Venda vendaMaisBarata = obterVendaComQuantidadePreco(11,1, new BigDecimal("5"), "Maria");
        Venda vendaMaisCara = obterVendaComQuantidadePreco(22, 1, new BigDecimal("20"), "Marlene");
        Venda vendaIntermediaria = obterVendaComQuantidadePreco(33, 3, new BigDecimal("2.50"), "Julia");
        resumoConsolidado = new ResumoConsolidado();
        resumoConsolidado.adicionarVenda(vendaMaisBarata);
        resumoConsolidado.adicionarVenda(vendaMaisCara);
        resumoConsolidado.adicionarVenda(vendaIntermediaria);

        assertEquals("Maria", resumoConsolidado.getNomePiorVendedor());
    }

    @Test
    public void aoSetarAtributosDeveriaRetornarResultadoEsperado() {
        List<Cliente> clientes = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        List<Vendedor> vendedores = new ArrayList<>();
        Venda venda = new Venda();
        venda.setId(1);
        ResumoConsolidado relatorio = new ResumoConsolidado();
        relatorio.setClientes(clientes);
        relatorio.setVendaMaisCara(venda);
        relatorio.setVendedores(vendedores);
        vendas.add(venda);
        relatorio.adicionarVenda(venda);

        assertSame(clientes, relatorio.getClientes());
        assertSame(venda, relatorio.getVendaMaisCara());
        assertSame(vendedores, relatorio.getVendedores());
        assertEquals(Integer.valueOf(1), relatorio.getVendas().get(0).getId());
    }

    @Test
    public void aoGerarDeveriaRetornarResumoNoFormatoDeString() {

        resumoConsolidado = new ResumoConsolidado();



        List<Cliente> clientes = new ArrayList<>();
        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setNome("Maria");
        clienteEsperado.setAreaNegocio("Business");
        clienteEsperado.setCnpj("111");
        clientes.add(clienteEsperado);

        List<Venda> vendas = new ArrayList<>();
        vendas.add(obterVendaComQuantidadePreco(9, 2, new BigDecimal("100"), "Joana"));
        vendas.add(obterVendaComQuantidadePreco(10, 2, new BigDecimal("200"), "Luiza"));

        List<Vendedor> vendedores = new ArrayList<>();
        Vendedor vendedoraJoana = new Vendedor();
        vendedoraJoana.setSalario(new BigDecimal("1000"));
        vendedoraJoana.setNome("Joana");
        vendedoraJoana.setCpf("222");
        vendedores.add(vendedoraJoana);

        Vendedor vendedoraLuiza = new Vendedor();
        vendedoraLuiza.setSalario(new BigDecimal("1000"));
        vendedoraLuiza.setNome("Luiza");
        vendedoraLuiza.setCpf("333");
        vendedores.add(vendedoraJoana);

        resumoConsolidado.setClientes(clientes);
        resumoConsolidado.setVendedores(vendedores);
        resumoConsolidado.adicionarVenda(obterVendaComQuantidadePreco(9, 2, new BigDecimal("100"), "Joana"));
        resumoConsolidado.adicionarVenda(obterVendaComQuantidadePreco(10, 2, new BigDecimal("200"), "Luiza"));


        String resultadoRetornado = resumoConsolidado.gerar();

        String resultadoEsperado = String.format("Clientes: %s;", "1") + System.lineSeparator();
        resultadoEsperado += String.format("Vendedores: %s;", "2") + System.lineSeparator();
        resultadoEsperado += String.format("ID da Venda mais cara: %s;", "10") + System.lineSeparator();
        resultadoEsperado += String.format("O pior vendedor: %s;", "Joana");

        assertEquals(resultadoEsperado, resultadoRetornado);
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
