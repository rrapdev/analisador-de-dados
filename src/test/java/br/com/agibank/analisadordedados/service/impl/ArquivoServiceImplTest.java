package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.service.ArquivoService;
import br.com.agibank.analisadordedados.service.component.GeradorEntidadeArquivo;
import br.com.agibank.analisadordedados.service.component.ManipuladorArquivos;
import br.com.agibank.analisadordedados.service.validador.ValidadorPath;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static br.com.agibank.analisadordedados.service.impl.ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_SAIDA_DADOS;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ArquivoServiceImplTest {

    ArquivoService arquivoServiceImpl;

    @Mock
    ManipuladorArquivos manipuladorArquivosMock;
    @Mock
    ValidadorPath validadorPathMock;
    @Mock
    GeradorEntidadeArquivo geradorEntidadeArquivoMock;
    @Mock
    Path pathEsperadoMock;
    @Mock
    Path outroPathEsperadoMock;
    @Mock
    Arquivo arquivoEsperado;
    @Mock Resumo resumoEsperado;

    Stream<Path> listaStreamPathEsperada;

    @Before
    public void inicializarContexto() {
        arquivoServiceImpl = new ArquivoServiceImpl(manipuladorArquivosMock, validadorPathMock, geradorEntidadeArquivoMock);
        listaStreamPathEsperada = Stream.of(pathEsperadoMock);
    }

    @Test
    public void aoLerArquivosDeveriaDelegarParaListarPathsDoDiretorioPadraoDeEntrada() {
        arquivoServiceImpl.lerArquivos();
        verify(manipuladorArquivosMock).listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS);
    }

    @Test
    public void aoLerArquivosDeveriaDelegarParaVerificarSePathCorrespondeAhUmArquivo() {
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        arquivoServiceImpl.lerArquivos();
        verify(validadorPathMock).verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock);
    }

    @Test
    public void aoLerArquivosDeveriaDelegarParaCriarEntidadeArquivo() {
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock)).thenReturn(true);
        arquivoServiceImpl.lerArquivos();
        verify(geradorEntidadeArquivoMock).criar(pathEsperadoMock);
    }

    @Test
    public void aoLerArquivosDeveriaRetornarArquivoEsperado() {
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock)).thenReturn(true);
        Mockito.when(geradorEntidadeArquivoMock.criar(pathEsperadoMock)).thenReturn(arquivoEsperado);
        List<Arquivo> listaArquivosRetornados = arquivoServiceImpl.lerArquivos();
        assertSame(arquivoEsperado, obterPrimeiroArquivoDaLista(listaArquivosRetornados));
    }

    @Test
    public void aoLerArquivosComPathNaoCorrespondenteAhUmArquivoNaoDeveriaDelegarParaGeradorEntidadeArquivo() {
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock)).thenReturn(false);
        List<Arquivo> listaArquivosRetornados = arquivoServiceImpl.lerArquivos();
        assertTrue(listaArquivosRetornados.isEmpty());
        verifyNoInteractions(geradorEntidadeArquivoMock);
    }

    @Test
    public void aoLerArquivosComDoisPathsSendoUmValidoEhOutroDeveriaRetornarListaComApenasUmArquivo() {
        listaStreamPathEsperada = Stream.of(pathEsperadoMock, outroPathEsperadoMock);
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock)).thenReturn(true);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(outroPathEsperadoMock)).thenReturn(false);
        Mockito.when(geradorEntidadeArquivoMock.criar(pathEsperadoMock)).thenReturn(arquivoEsperado);
        List<Arquivo> listaArquivosRetornados = arquivoServiceImpl.lerArquivos();
        assertEquals(1, listaArquivosRetornados.size());
    }

    @Test
    public void aoLerArquivosComDoisPathsValidosDeveriaRetornarListaComDoisArquivos() {
        listaStreamPathEsperada = Stream.of(pathEsperadoMock, outroPathEsperadoMock);
        Mockito.when(manipuladorArquivosMock.listarPathsDoDiretorio(ArquivoServiceImpl.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS)).thenReturn(listaStreamPathEsperada);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(pathEsperadoMock)).thenReturn(true);
        Mockito.when(validadorPathMock.verificarSePathCorrespondeAhUmArquivo(outroPathEsperadoMock)).thenReturn(true);
        Mockito.when(geradorEntidadeArquivoMock.criar(pathEsperadoMock)).thenReturn(arquivoEsperado);
        List<Arquivo> listaArquivosRetornados = arquivoServiceImpl.lerArquivos();
        assertEquals(2, listaArquivosRetornados.size());
    }

    @Test
    public void aoGerarArquivoConsolidadoDeveriaDelegarParaCriarArquivoContendoResumo() {
        arquivoServiceImpl.gerarArquivoConsolidado(resumoEsperado, arquivoEsperado);
        verify(manipuladorArquivosMock).criarArquivoContendoResumoNoDiretorioPadraoSaida(resumoEsperado,
                CAMINHO_DIRETORIO_PADRAO_SAIDA_DADOS + arquivoEsperado.obterNomeDoArquivoConsolidado());
    }

    @Test
    public void aoApagarArquivoProcessadoDeveriaDelegarParaManipuladorArquivoApagar() {
        arquivoServiceImpl.apagarArquivoProcessado(arquivoEsperado);
        verify(manipuladorArquivosMock).apagarArquivo(arquivoEsperado);
    }

    private Arquivo obterPrimeiroArquivoDaLista(List<Arquivo> listaArquivosRetornados) {
        return listaArquivosRetornados.get(0);
    }
}