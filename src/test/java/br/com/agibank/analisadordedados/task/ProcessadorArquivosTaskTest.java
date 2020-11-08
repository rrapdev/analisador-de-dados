package br.com.agibank.analisadordedados.task;

import br.com.agibank.analisadordedados.service.ProcessadorArquivos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProcessadorArquivosTaskTest {

    ProcessadorArquivosTaskImpl processadorArquivosTaskImpl;

    @Mock
    ProcessadorArquivos processadorArquivosMock;

    @Before
    public void inicializarContexto() {
        processadorArquivosTaskImpl = new ProcessadorArquivosTaskImpl(processadorArquivosMock);
    }

    @Test
    public void aoProcessarNovosArquivosDeVendasDeveriaDelegarParaProcessar() {
        processadorArquivosTaskImpl.processarNovosArquivosDeVendas();
        verify(processadorArquivosMock).processar();
    }
}