package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.ResumoConsolidado;
import br.com.agibank.analisadordedados.service.AnalisadorDadosService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class AnalisadorDadosServiceImplTest {

    AnalisadorDadosService analisadorDadosServiceImpl;

    @Mock
    Arquivo arquivo;

    @Before
    public void inicializarContexto() {
        analisadorDadosServiceImpl = new AnalisadorDadosServiceImpl();
    }

    @Test
    public void aoAnalisarConteudoDoArquivoComVendedorDeveriaRetornarDadosVendedor() {
        String conteudoArquivo = "001ç1234567891234çPedroç50000";
        Mockito.when(arquivo.obterConteudoDoArquivo()).thenReturn(conteudoArquivo);

        ResumoConsolidado resumo = (ResumoConsolidado) analisadorDadosServiceImpl.analisarConteudo(arquivo);

        assertFalse(resumo.getVendedores().isEmpty());
        assertEquals("Pedro", resumo.getVendedores().get(0).getNome());
        assertEquals(new BigDecimal("50000"), resumo.getVendedores().get(0).getSalario());
        assertEquals("1234567891234", resumo.getVendedores().get(0).getCpf());
    }

    @Test
    public void aoAnalisarConteudoDoArquivoComClienteDeveriaRetornarDadosCliente() {
        String conteudoArquivo = "002ç2345675434544345çJose da SilvaçRural";
        Mockito.when(arquivo.obterConteudoDoArquivo()).thenReturn(conteudoArquivo);

        ResumoConsolidado resumo = (ResumoConsolidado) analisadorDadosServiceImpl.analisarConteudo(arquivo);

        assertFalse(resumo.getClientes().isEmpty());
        assertEquals("Jose da Silva", resumo.getClientes().get(0).getNome());
        assertEquals("2345675434544345", resumo.getClientes().get(0).getCnpj());
        assertEquals("Rural", resumo.getClientes().get(0).getAreaNegocio());
    }

    @Test
    public void aoAnalisarConteudoDoArquivoComVendaDeveriaRetornarDadosVenda() {
        String conteudoArquivo = "003ç10ç[1-10-100,2-30-2.50]çPedro";
        Mockito.when(arquivo.obterConteudoDoArquivo()).thenReturn(conteudoArquivo);

        ResumoConsolidado resumo = (ResumoConsolidado) analisadorDadosServiceImpl.analisarConteudo(arquivo);

        assertFalse(resumo.getVendas().isEmpty());
        assertEquals(Integer.valueOf(10), resumo.getVendas().get(0).getId());
        assertEquals("Pedro", resumo.getVendas().get(0).getNomeVendedor());
        assertEquals(Integer.valueOf(1), resumo.getVendas().get(0).getItensVenda().get(0).getId());
        assertEquals(Integer.valueOf(10), resumo.getVendas().get(0).getItensVenda().get(0).getQuantidade());
        assertEquals(new BigDecimal(100), resumo.getVendas().get(0).getItensVenda().get(0).getPreco());
        assertEquals(Integer.valueOf(2), resumo.getVendas().get(0).getItensVenda().get(1).getId());
        assertEquals(Integer.valueOf(30), resumo.getVendas().get(0).getItensVenda().get(1).getQuantidade());
        assertEquals(new BigDecimal("2.50"), resumo.getVendas().get(0).getItensVenda().get(1).getPreco());
    }

    @Test
    public void aoAnalisarConteudoDoArquivoComClienteVendedorEhVendasDeveriaRetornarDadosEsperados() {
        String conteudoArquivo = "001ç1234567891234çPedroç50000\n";
        conteudoArquivo += "001ç3245678865434çPauloç40000.99\n";
        conteudoArquivo += "002ç2345675434544345çJose da SilvaçRural\n";
        conteudoArquivo += "002ç1234çEduardo PereiraçRural\n";
        conteudoArquivo += "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n";
        conteudoArquivo += "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";

        Mockito.when(arquivo.obterConteudoDoArquivo()).thenReturn(conteudoArquivo);

        ResumoConsolidado resumo = (ResumoConsolidado) analisadorDadosServiceImpl.analisarConteudo(arquivo);

        assertFalse(resumo.getVendedores().isEmpty());
        assertFalse(resumo.getClientes().isEmpty());
        assertFalse(resumo.getVendas().isEmpty());
        assertEquals(2, resumo.getVendedores().size());
        assertEquals(2, resumo.getClientes().size());
        assertEquals(2, resumo.getVendas().size());

        assertEquals("Paulo", resumo.getVendedores().get(1).getNome());
        assertEquals(new BigDecimal("40000.99"), resumo.getVendedores().get(1).getSalario());
        assertEquals("3245678865434", resumo.getVendedores().get(1).getCpf());

        assertEquals("Eduardo Pereira", resumo.getClientes().get(1).getNome());
        assertEquals("1234", resumo.getClientes().get(1).getCnpj());
        assertEquals("Rural", resumo.getClientes().get(1).getAreaNegocio());

        assertEquals(Integer.valueOf(8), resumo.getVendas().get(1).getId());
        assertEquals("Paulo", resumo.getVendas().get(1).getNomeVendedor());
        assertEquals(Integer.valueOf(3), resumo.getVendas().get(1).getItensVenda().get(2).getId());
        assertEquals(Integer.valueOf(40), resumo.getVendas().get(1).getItensVenda().get(2).getQuantidade());
        assertEquals(new BigDecimal("0.10"), resumo.getVendas().get(1).getItensVenda().get(2).getPreco());
    }
}