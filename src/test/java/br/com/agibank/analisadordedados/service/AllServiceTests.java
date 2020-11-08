package br.com.agibank.analisadordedados.service;

import br.com.agibank.analisadordedados.service.component.GeradorEntidadeArquivoImplTest;
import br.com.agibank.analisadordedados.service.component.ManipuladorArquivosImplTest;
import br.com.agibank.analisadordedados.service.impl.AnalisadorDadosServiceImplTest;
import br.com.agibank.analisadordedados.service.impl.ArquivoServiceImplTest;
import br.com.agibank.analisadordedados.service.impl.ProcessadorArquivosImplTest;
import br.com.agibank.analisadordedados.service.validador.impl.ValidadorPathImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ProcessadorArquivosImplTest.class,
        ArquivoServiceImplTest.class,
        ManipuladorArquivosImplTest.class,
        AnalisadorDadosServiceImplTest.class,
        ValidadorPathImplTest.class,
        GeradorEntidadeArquivoImplTest.class
})
public class AllServiceTests {
}
