package br.com.agibank.analisadordedados.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.DADOS_VENDEDOR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class VendedorTest {

    private Vendedor vendedor;

    @Before
    public void inicializarContexto() {
        vendedor = new Vendedor();
    }

    @Test
    public void aoExecutarMetodoImportarDadosDeveriaAtribuirDados() {
        vendedor.importarDados(DADOS_VENDEDOR);
        assertEquals(DADOS_VENDEDOR[1], vendedor.getCpf());
        assertEquals(DADOS_VENDEDOR[2], vendedor.getNome());
        assertEquals(new BigDecimal(DADOS_VENDEDOR[3]), vendedor.getSalario());
    }

    @Test
    public void aoExecutarMetodoAdicionarAoRelatorioDeveriaDeveriaRetornarRelatorioComVendedorAdicionado() {
        vendedor = new Vendedor();
        RelatorioResumoVendas relatorio = new RelatorioResumoVendas();
        vendedor.adicionarAoRelatorio(relatorio);
        Vendedor retorno = relatorio.getVendedores().get(0);
        assertSame(vendedor, retorno);
    }

    @Test
    public void aoSetarAtributosDeveriaRetornarDadosEsperados() {
        vendedor = new Vendedor();
        vendedor.setCpf("123");
        vendedor.setNome("nome qualquer");
        vendedor.setSalario(new BigDecimal("12000"));
        assertEquals("123", vendedor.getCpf());
        assertEquals("nome qualquer", vendedor.getNome());
        assertEquals(new BigDecimal("12000"), vendedor.getSalario());
    }

}
