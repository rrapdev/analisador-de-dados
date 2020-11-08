package br.com.agibank.analisadordedados.domain;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.LinhaArquivoDataTable;
import br.com.agibank.analisadordedados.exception.FalhaAoAcessarDiretorioArquivoException;
import br.com.agibank.analisadordedados.exception.NegocioException;
import br.com.agibank.analisadordedados.util.ManipuladorArquivoTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.*;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ArquivoVendaTest {

    public static final String NOME_ARQUIVO_VALIDO_DAT = "nome-arquivo-valido.dat";
    public static final String TEXTO_DENTRO_DO_ARQUIVO = "Texto dentro do arquivo.\nSegunda linha.";
    private ArquivoVenda arquivoVenda;

    private ManipuladorArquivoTestHelper manipuladorArquivoTestHelper = new ManipuladorArquivoTestHelper();

    @Before
    public void inicializarContexto() {
        this.limparContexto();
        arquivoVenda = new ArquivoVenda();
        criarArquivoQualquerComConteudoEhNome(NOME_ARQUIVO_VALIDO_DAT, TEXTO_DENTRO_DO_ARQUIVO);
    }

    @Test
    public void aoValidarComPathArquivoExtensaoValidaDatDeveriaNaoGerarExcecao() {
        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/" + NOME_ARQUIVO_VALIDO_DAT);
        arquivoVenda.setPath(path);
        arquivoVenda.validar();
    }

    @Test(expected = NegocioException.class)
    public void aoValidarComPathArquivoExtensaoDiferenteDeDatDeveriaGerarExcecao() {
        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/nome-arquivo-invalido.txt");
        arquivoVenda.setPath(path);
        arquivoVenda.validar();
    }

    @Test
    public void aoObterConteudoDoArquivoDeveriaRetornarConteudoEsperado() {
        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/" + NOME_ARQUIVO_VALIDO_DAT);
        arquivoVenda.setPath(path);
        String conteudo = arquivoVenda.obterConteudoDoArquivo();
        assertEquals(TEXTO_DENTRO_DO_ARQUIVO, conteudo);
    }

    @Test(expected = FalhaAoAcessarDiretorioArquivoException.class)
    public void aoObterConteudoDoArquivoComPathInvalidoDeveriaLancarExcecao() {
        Path path = Path.of("caminho/invalido");
        arquivoVenda.setPath(path);
        String conteudo = arquivoVenda.obterConteudoDoArquivo();
    }

    @Test
    public void aoObterNomeDoArquivoConsolidadDeveriaRetornarNomeEsperado() {
        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/" + NOME_ARQUIVO_VALIDO_DAT);
        arquivoVenda.setPath(path);
        String nomeArquivoConsolidado = arquivoVenda.obterNomeDoArquivoConsolidado();
        assertEquals("nome-arquivo-valido.done.dat", nomeArquivoConsolidado);
    }

    private ArquivoDataTable criarArquivoQualquerComConteudoEhNome(String nomeArquivo, String conteudo) {
        ArquivoDataTable arquivoAhSerCriado = new ArquivoDataTable();
        arquivoAhSerCriado.setNome(nomeArquivo);
        arquivoAhSerCriado.setDiretorio(DIRETORIO_DATA_IN);
        List<LinhaArquivoDataTable> linhas = new ArrayList<>();
        LinhaArquivoDataTable linha1 = new LinhaArquivoDataTable();
        linha1.setDados(conteudo);
        linhas.add(linha1);
        arquivoAhSerCriado.setLinhas(linhas);
        manipuladorArquivoTestHelper.criarArquivo(arquivoAhSerCriado);
        return arquivoAhSerCriado;
    }

    public void limparContexto() {
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio(DIRETORIO_DATA_IN);
    }
}