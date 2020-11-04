package br.com.agibank.analisadordedados.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class TipoDadoTest {

    @Test
    public void aoExecutarMetodoDescobrirComTipoDadoVendedorDeveriaRetornarEntidadeVendedor() {
        TipoDado vendedor = TipoDado.descobrir(DADOS_VENDEDOR);
        assertTrue(vendedor instanceof Vendedor);
        verificarSeDadosVendedorFormaImportados((Vendedor) vendedor);
    }

    @Test
    public void aoExecutarMetodoDescobrirComTipoDadoClienteDeveriaRetornarEntidadeCliente() {
        TipoDado cliente = TipoDado.descobrir(DADOS_CLIENTE);
        assertTrue(cliente instanceof Cliente);
        verificarSeDadosClienteFormaImportados((Cliente) cliente);
    }

    @Test
    public void aoExecutarMetodoDescobrirComTipoDadoVendaDeveriaRetornarEntidadeVenda() {
        TipoDado venda = TipoDado.descobrir(DADOS_VENDA);
        assertTrue(venda instanceof Venda);
        verificarSeDadosVendasForamImportados((Venda) venda);
    }

    private void verificarSeDadosVendedorFormaImportados(Vendedor vendedor) {
        assertEquals(DADOS_VENDEDOR[1], vendedor.getCpf());
        assertEquals(DADOS_VENDEDOR[2], vendedor.getNome());
        assertEquals(new BigDecimal(DADOS_VENDEDOR[3]), vendedor.getSalario());
    }

    private void verificarSeDadosClienteFormaImportados(Cliente cliente) {
        assertEquals(DADOS_CLIENTE[1], cliente.getCnpj());
        assertEquals(DADOS_CLIENTE[2], cliente.getNome());
        assertEquals(DADOS_CLIENTE[3], cliente.getAreaNegocio());
    }

    private void verificarSeDadosVendasForamImportados(Venda venda) {
        assertEquals(Integer.valueOf(DADOS_VENDA[1]), venda.getId());
        assertEquals(Integer.valueOf(ID_ITEM_VENDA_1), venda.getItensVenda().get(0).getId());
        assertEquals(Integer.valueOf(QUANTIDADE_ITEM_VENDA_10), venda.getItensVenda().get(0).getQuantidade());
        assertEquals(new BigDecimal(PRECO_ITEM_VENDA_100), venda.getItensVenda().get(0).getPreco());
        assertEquals(DADOS_VENDA[3], venda.getNomeVendedor());
    }

    public static final int ID_ITEM_VENDA_1 = 1;
    public static final int QUANTIDADE_ITEM_VENDA_10 = 10;
    public static final int PRECO_ITEM_VENDA_100 = 100;
}
