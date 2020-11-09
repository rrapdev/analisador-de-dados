package br.com.agibank.analisadordedados.domain;

import br.com.agibank.analisadordedados.builder.ClienteBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ClienteTest {

    private Cliente cliente;

    @Before
    public void inicializarContexto() {
        cliente = ClienteBuilder.umCliente().build();
    }

    @Test
    public void aoExecutarMetodoImportarDadosDeveriaAtribuirDadosParaCnpjNomeEhAreaDeAtuacao() {
        String[] dados = {TIPO_CLIENTE_002, CNPJ_123456, NOME_KENT_BECK, AREA_NEGOCIO_TESTES};
        cliente.importarDados(dados);
        assertEquals(CNPJ_123456, cliente.getCnpj());
        assertEquals(NOME_KENT_BECK, cliente.getNome());
        assertEquals(AREA_NEGOCIO_TESTES, cliente.getAreaNegocio());
    }

    @Test
    public void aoExecutarMetodoAdicionarAoRelatorioDeveriaDeveriaRetornarRelatorioComClienteAdicionado() {
        cliente = ClienteBuilder.umClienteValido().build();
        ResumoConsolidado relatorio = new ResumoConsolidado();
        cliente.adicionarAoRelatorio(relatorio);
        Cliente retorno = relatorio.getClientes().get(0);
        assertSame(cliente, retorno);
    }

    @Test
    public void aoExecutarMetodosGetsSets() {
        cliente = new Cliente();
        cliente.setCnpj(CNPJ_123456);
        cliente.setNome(NOME_KENT_BECK);
        cliente.setAreaNegocio(AREA_NEGOCIO_TESTES);
        assertTrue(cliente.equals(cliente));
        assertEquals(1075161335, cliente.hashCode());
        assertEquals(NOME_KENT_BECK, cliente.getNome());
        assertEquals(CNPJ_123456, cliente.getCnpj());
        assertEquals(AREA_NEGOCIO_TESTES, cliente.getAreaNegocio());
    }

    public static final String CNPJ_123456 = "123456";
    public static final String NOME_KENT_BECK = "Kent Beck";
    public static final String AREA_NEGOCIO_TESTES = "Testes";
    public static final String TIPO_CLIENTE_002 = "002";
}
