package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.service.AnalisadorDadosService;
import br.com.agibank.analisadordedados.service.ArquivoService;
import br.com.agibank.analisadordedados.service.ProcessadorArquivos;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProcessadorArquivosImplTest {

    ProcessadorArquivos processadorArquivosImpl;

    @Mock
    private AnalisadorDadosService analisadorDadosServiceMock;

    @Mock
    private ArquivoService arquivoServiceMock;

    @Mock
    Arquivo arquivoEsperadoMock;

    @Mock
    Arquivo outroArquivoEsperadoMock;

    @Mock
    Resumo resumoEsperadoMock;

    @Mock
    Resumo outroResumoEsperadoMock;

    List<Arquivo> arquivosEsperados;

    @Before
    public void inicializarContexto() {
        processadorArquivosImpl = new ProcessadorArquivosImpl(arquivoServiceMock, analisadorDadosServiceMock);
        arquivosEsperados = new ArrayList<>();
        arquivosEsperados.add(arquivoEsperadoMock);
    }

    @Test
    public void aoProcessarDeveriaDelegarParaArquivoServiceLerArquivos() {
        processadorArquivosImpl.processar();
        verify(arquivoServiceMock).lerArquivos();
    }

    @Test
    public void aoProcessarDeveriaValidarArquivoRetornado() {
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);

        processadorArquivosImpl.processar();

        verify(arquivoEsperadoMock).validar();
    }

    @Test
    public void aoProcessarDeveriaAnalisarArquivoRetornado() {
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);

        processadorArquivosImpl.processar();

        verify(analisadorDadosServiceMock).analisarConteudo(arquivoEsperadoMock);
    }

    @Test
    public void aoProcessarDeveriaGerarArquivoComResumo() {
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);
        Mockito.when(analisadorDadosServiceMock.analisarConteudo(arquivoEsperadoMock)).thenReturn(resumoEsperadoMock);

        processadorArquivosImpl.processar();

        verify(arquivoServiceMock).gerarArquivoConsolidado(resumoEsperadoMock, arquivoEsperadoMock);
    }

    @Test
    public void aoProcessarComListaDeArquivosDeveriaValidarTodosOsArquivosRetornados() {
        arquivosEsperados.add(outroArquivoEsperadoMock);
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);

        processadorArquivosImpl.processar();

        verify(arquivoEsperadoMock).validar();
        verify(outroArquivoEsperadoMock).validar();
    }

    @Test
    public void aoProcessarComListaDeArquivosDeveriaAnalisarTodosOsArquivos() {
        arquivosEsperados.add(outroArquivoEsperadoMock);
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);

        processadorArquivosImpl.processar();

        verify(analisadorDadosServiceMock).analisarConteudo(arquivoEsperadoMock);
        verify(analisadorDadosServiceMock).analisarConteudo(outroArquivoEsperadoMock);
    }

    @Test
    public void aoProcessarComListaDeArquivosDeveriaGerarArquivosComResumo() {
        arquivosEsperados.add(outroArquivoEsperadoMock);
        Mockito.when(arquivoServiceMock.lerArquivos()).thenReturn(arquivosEsperados);
        Mockito.when(analisadorDadosServiceMock.analisarConteudo(arquivoEsperadoMock)).thenReturn(resumoEsperadoMock);
        Mockito.when(analisadorDadosServiceMock.analisarConteudo(outroArquivoEsperadoMock)).thenReturn(outroResumoEsperadoMock);

        processadorArquivosImpl.processar();

        verify(arquivoServiceMock).gerarArquivoConsolidado(resumoEsperadoMock, arquivoEsperadoMock);
        verify(arquivoServiceMock).gerarArquivoConsolidado(outroResumoEsperadoMock, outroArquivoEsperadoMock);
    }

}