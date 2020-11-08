package br.com.agibank.analisadordedados.service.component;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.ArquivoVenda;
import br.com.agibank.analisadordedados.service.component.impl.GeradorEntidadeArquivoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;

import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class GeradorEntidadeArquivoImplTest {

    GeradorEntidadeArquivo geradorEntidadeArquivoImpl;

    @Before
    public void inicializarContexto() {
        geradorEntidadeArquivoImpl = new GeradorEntidadeArquivoImpl();
    }

    @Test
    public void aoCriarDeveriaRetornarArquivoComPathSetado() {
        Path pathEsperado = Path.of("path_qualquer");
        ArquivoVenda arquivoCriado = (ArquivoVenda) geradorEntidadeArquivoImpl.criar(pathEsperado);
        assertSame(pathEsperado, arquivoCriado.getPath());
    }
}
