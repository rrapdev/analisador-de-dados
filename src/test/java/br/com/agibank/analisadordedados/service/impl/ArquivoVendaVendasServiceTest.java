package br.com.agibank.analisadordedados.service.impl;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.LinhaArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.ResumoDataTable;
import br.com.agibank.analisadordedados.service.ArquivoService;
import br.com.agibank.analisadordedados.util.ManipuladorArquivoTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.DIRETORIO_DATA_IN;
import static br.com.agibank.analisadordedados.util.ConstantesUtil.DIRETORIO_DATA_OUT;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ArquivoVendaVendasServiceTest {
    private ArquivoService arquivoVendasService;
    private ManipuladorArquivoTestHelper manipuladorArquivoTestHelper = new ManipuladorArquivoTestHelper();
    public static final String NOME_ARQUIVO_DE_TESTE_DAT = "arquivo-de-teste.dat";
    public static final String NOME_ARQUIVO_DE_TESTE_DONE_DAT = "arquivo-de-teste.done.dat";
    public static final String NOME_ARQUIVO_FORA_PADRAO = "arquivo-fora-padrao.csv";

    @Before
    public void inicializarContexto() {
        this.limparContexto();
        arquivoVendasService = new ArquivoVendasService();
    }

    @Test
    public void aoProcessarDeveriaRetornarNomesDosArquivosProcessados() {
        criarArquivoDeVendasComNome(NOME_ARQUIVO_DE_TESTE_DAT);
        List<String> retorno = arquivoVendasService.processar();
        assertNotNull("Lista de arquivos processados não deveria ser nula.", retorno);
        assertFalse("Lista de arquivos processados não deveria ser vazia.", retorno.isEmpty());
        assertEquals("Lista de arquivos processados não corresponde com a lista esperada.",
                NOME_ARQUIVO_DE_TESTE_DAT, retorno.get(0));
    }

    @Test
    public void aoProcessarDeveriaGerarArquivosEmDiretorioDeSaidaComNomeEsperado() {
        criarArquivoDeVendasComNome(NOME_ARQUIVO_DE_TESTE_DAT);
        arquivoVendasService.processar();
        verificarSeArquivoExisteEmDiretorioSaida(NOME_ARQUIVO_DE_TESTE_DONE_DAT);
    }

    @Test
    public void aoProcessarComNomeArquivoForaDoPadraoDeveriaIgnorarProcessamento() {
        criarArquivoDeVendasComNome(NOME_ARQUIVO_FORA_PADRAO);
        arquivoVendasService.processar();
        assertFalse(verificarSeExisteAlgumArquivoEmDiretorioSaida());
    }

    @Test
    public void aoProcessarDeveriaGerarArquivoComDadosEsperados() {
        ArquivoDataTable arquivoCriado = criarArquivoDeVendasComNome(NOME_ARQUIVO_DE_TESTE_DAT);
        arquivoVendasService.processar();

        arquivoCriado.setDiretorio("data/out");
        ResumoDataTable resumoArquivoGerado = manipuladorArquivoTestHelper.lerArquivoRetornandoDataTable(arquivoCriado);
        assertEquals(Integer.valueOf(2), resumoArquivoGerado.getQuantidadeClientes());
        assertEquals(Integer.valueOf(2), resumoArquivoGerado.getQuantidadeVendedores());
        assertEquals(Integer.valueOf(10), resumoArquivoGerado.getIdVendaMaisCara());
        assertEquals("Paulo", resumoArquivoGerado.getNomePiorVendedor());
    }

    private boolean verificarSeExisteAlgumArquivoEmDiretorioSaida() {
        return manipuladorArquivoTestHelper.verificarSeExisteAlgumArquivoEmDiretorioSaida("data/out");
    }

    private void verificarSeArquivoExisteEmDiretorioSaida(String nomeArquivo) {
        ArquivoDataTable arquivo = new ArquivoDataTable();
        arquivo.setNome(nomeArquivo);
        arquivo.setDiretorio(DIRETORIO_DATA_OUT);
        assertTrue(manipuladorArquivoTestHelper.verificarSeArquivoExiste(arquivo));
    }

    private ArquivoDataTable criarArquivoDeVendasComNome(String nomeArquivo) {
        ArquivoDataTable arquivoAhSerCriado = new ArquivoDataTable();
        arquivoAhSerCriado.setNome(nomeArquivo);
        arquivoAhSerCriado.setDiretorio(DIRETORIO_DATA_IN);
        arquivoAhSerCriado.setLinhas(gerarLinhasValidasDoArquivo());
        manipuladorArquivoTestHelper.criarArquivo(arquivoAhSerCriado);
        return arquivoAhSerCriado;
    }

    private List<LinhaArquivoDataTable> gerarLinhasValidasDoArquivo() {
        List<LinhaArquivoDataTable> linhas = new ArrayList<>();
        linhas.add(gerarLinha("001ç1234567891234çPedroç50000"));
        linhas.add(gerarLinha("001ç3245678865434çPauloç40000.99"));
        linhas.add(gerarLinha("002ç2345675434544345çJose da SilvaçRural"));
        linhas.add(gerarLinha("002ç2345675433444345çEduardo PereiraçRural"));
        linhas.add(gerarLinha("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro"));
        linhas.add(gerarLinha("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo"));
        return linhas;
    }

    private LinhaArquivoDataTable gerarLinha(String dados) {
        LinhaArquivoDataTable linha = new LinhaArquivoDataTable();
        linha.setDados(dados);
        return linha;
    }

    public void limparContexto() {
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio(DIRETORIO_DATA_IN);
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio(DIRETORIO_DATA_OUT);
    }
}