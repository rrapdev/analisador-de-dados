package br.com.agibank.analisadordedados.service.component;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.domain.Arquivo;
import br.com.agibank.analisadordedados.domain.Resumo;
import br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException;
import br.com.agibank.analisadordedados.service.component.impl.ManipuladorArquivosImpl;
import br.com.agibank.analisadordedados.util.ManipuladorArquivoTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.stream.Stream;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ManipuladorArquivosImplTest {

    ManipuladorArquivos manipuladorArquivosImpl;

    @Mock
    Resumo resumoMock;

    @Mock
    Arquivo arquivoMock;

    private ManipuladorArquivoTestHelper manipuladorArquivoTestHelper = new ManipuladorArquivoTestHelper();

    @Before
    public void inicializarContexto() {
        this.limparContexto();
        manipuladorArquivosImpl = new ManipuladorArquivosImpl();
        criarArquivoDeVendasComNome(NOME_ARQUIVO_TXT);
    }

    @Test
    public void aoListarPathsDoDiretoriComUmArquivoNoDiretorioDeveriaRetornarPathDeArquivo() {
        Stream<Path> pathStream = manipuladorArquivosImpl.listarPathsDoDiretorio(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS);
        Path pathRetornado = pathStream.findFirst().get();
        assertEquals(NOME_ARQUIVO_TXT, pathRetornado.getFileName().toString());
    }

    @Test
    public void aoListarPathsDoDiretorioSemNenhumArquivoNoDiretorioDeveriaRetornarPathDeArquivoVazio() {
        this.limparContexto();
        Stream<Path> pathStream = manipuladorArquivosImpl.listarPathsDoDiretorio(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS);
        assertEquals(0, pathStream.count());
    }

    @Test(expected = FalhaAoAcessarDiretorioArquivoException.class)
    public void aoListarPathsDoDiretorioComCaminhoDiretorioIncorreroDeveriaLancarExcecao() {
        manipuladorArquivosImpl.listarPathsDoDiretorio("caminho/incorreto");
    }

    @Test
    public void aoCriarArquivoContendoResumoDeveriaCriarArquivoEmCaminhoPassado() {
        this.limparContexto();
        String textoArquivoEsperado = "Arquivo gerado.";
        String nomeArquivoSaida = "arquivo-gerado.txt";

        Mockito.when(resumoMock.gerar()).thenReturn(textoArquivoEsperado);

        manipuladorArquivosImpl.criarArquivoContendoResumoNoDiretorioPadraoSaida(resumoMock,
                            CAMINHO_DIRETORIO_PADRAO_SAIDA_DADOS + nomeArquivoSaida);

        ArquivoDataTable arquivoDataTable = new ArquivoDataTable();
        arquivoDataTable.setDiretorio("data/out");
        arquivoDataTable.setNome(nomeArquivoSaida);
        Stream<String> conteudoArquivo = manipuladorArquivoTestHelper.lerArquivo(arquivoDataTable);
        assertEquals(textoArquivoEsperado, conteudoArquivo.findFirst().get());
    }

    @Test(expected = FalhaAoAcessarDiretorioArquivoException.class)
    public void aoCriarArquivoComCaminhoInvalidoDeveriaLancarExcecao() {
        String caminhoInvalido = "caminho/invalido/arquivo.txt";
        manipuladorArquivosImpl.criarArquivoContendoResumoNoDiretorioPadraoSaida(resumoMock,
                caminhoInvalido);
    }

    @Test
    public void aoApagarArquivoDeveriaApagarComoEsperado() {
        Path pathEsperado = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/" + NOME_ARQUIVO_TXT);
        Mockito.when(arquivoMock.getPath()).thenReturn(pathEsperado);
        manipuladorArquivosImpl.apagarArquivo(arquivoMock);
        assertFalse(manipuladorArquivoTestHelper.verificarSeExisteAlgumArquivoEmDiretorio("data/out"));
    }

    @Test(expected = FalhaAoAcessarDiretorioArquivoException.class)
    public void aoApagarArquivoComCaminhoInvalidoDeveriaLancarExcecao() {
        Path pathEsperado = Path.of("caminho/invalido");
        Mockito.when(arquivoMock.getPath()).thenReturn(pathEsperado);
        manipuladorArquivosImpl.apagarArquivo(arquivoMock);
    }

    private ArquivoDataTable criarArquivoDeVendasComNome(String nomeArquivo) {
        ArquivoDataTable arquivoAhSerCriado = new ArquivoDataTable();
        arquivoAhSerCriado.setNome(nomeArquivo);
        arquivoAhSerCriado.setDiretorio(DIRETORIO_DATA_IN);
        manipuladorArquivoTestHelper.criarArquivo(arquivoAhSerCriado);
        return arquivoAhSerCriado;
    }

    public void limparContexto() {
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio(DIRETORIO_DATA_IN);
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio(DIRETORIO_DATA_OUT);
    }

    public static final String NOME_ARQUIVO_TXT = "arquivo.txt";
}