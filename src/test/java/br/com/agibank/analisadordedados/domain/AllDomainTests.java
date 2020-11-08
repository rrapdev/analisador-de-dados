package br.com.agibank.analisadordedados.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArquivoVendaTest.class,
        ClienteTest.class,
        ItemVendaTest.class,
        ResumoConsolidadoTest.class,
        TipoDadoTest.class,
        VendaTest.class,
        VendedorTest.class
})
public class AllDomainTests {
}
