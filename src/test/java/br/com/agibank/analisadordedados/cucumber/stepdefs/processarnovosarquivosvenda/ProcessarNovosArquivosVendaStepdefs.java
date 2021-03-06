package br.com.agibank.analisadordedados.cucumber.stepdefs.processarnovosarquivosvenda;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.LinhaArquivoDataTable;
import br.com.agibank.analisadordedados.cucumber.datatable.ResumoDataTable;
import br.com.agibank.analisadordedados.cucumber.stepdefs.Stepdefs;
import br.com.agibank.analisadordedados.exception.NegocioException;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.*;
import static org.junit.Assert.assertEquals;

public class ProcessarNovosArquivosVendaStepdefs extends Stepdefs {

    public static final boolean EXPECTATIVA_QUE_O_ARQUIVO_EXISTA = true;
    public static final boolean EXPECTATIVA_QUE_NAO_EXISTA_ARQUIVOS = false;
    private final ArquivoDataTable arquivoEntrada = new ArquivoDataTable();

    private String mensagemExceptionEsperada = "O arquivo a ser processado não possui extensão válida .dat!";
    private String mensagemExceptionRetornada = "";

    @Before
    public void inicializarContexto() {
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio("data/in");
        manipuladorArquivoTestHelper.deletarArquivosEmDiretorio("data/out");
    }

    @Dado("^que existe arquivo com o nome \"([^\"]*)\" na pasta \"([^\"]*)\"$")
    public void queExisteArquivoComONomeNaPasta(String nomeArquivoEntrada, String diretorioEntrada) throws Throwable {
        arquivoEntrada.setNome(nomeArquivoEntrada);
        arquivoEntrada.setDiretorio(diretorioEntrada);
        arquivoEntrada.setLinhas(gerarLinhasValidasDoArquivo());
        manipuladorArquivoTestHelper.criarArquivo(arquivoEntrada);
    }

    @Dado("^que existe um arquivo, a ser processado, com os seguintes dados$")
    public void queExisteUmArquivoASerProcessadoComOsSeguintesDados(List<LinhaArquivoDataTable> linhasArquivo) {
        ArquivoDataTable arquivo = new ArquivoDataTable();
        arquivo.setNome(NOME_ARQUIVO_VALIDO_DAT);
        arquivo.setDiretorio(DIRETORIO_DATA_IN);
        arquivo.setLinhas(linhasArquivo);
        manipuladorArquivoTestHelper.criarArquivo(arquivo);
    }

    @Quando("^passarem dez segundos$")
    public void passaremDezSegundos() throws InterruptedException {
        Thread.sleep(10000);
    }

    @Quando("^processar arquivos de vendas$")
    public void processarArquivosDeVendas() {
        try {
            processadorArquivosTask.processarNovosArquivosDeVendas();
        } catch (NegocioException e) {
            mensagemExceptionRetornada = e.getMessage();
        }

    }

    @Entao("^deveria gerar arquivo com nome \"([^\"]*)\" na pasta \"([^\"]*)\"$")
    public void deveriaGerarArquivoComNomeNaPasta(String nomeArquivoSaida, String diretorioSaida) throws Throwable {
        ArquivoDataTable arquivoSaidaEsperado = new ArquivoDataTable(nomeArquivoSaida, diretorioSaida);
        assertEquals(EXPECTATIVA_QUE_O_ARQUIVO_EXISTA,
                     manipuladorArquivoTestHelper.verificarSeArquivoExiste(arquivoSaidaEsperado));
    }

    @Entao("^deveria nao gerar nenhum arquivo na pasta \"([^\"]*)\"$")
    public void deveriaNaoGerarNenhumArquivoNaPasta(String diretorioSaida) throws Throwable {

        assertEquals(EXPECTATIVA_QUE_NAO_EXISTA_ARQUIVOS,
                manipuladorArquivoTestHelper.verificarSeExisteAlgumArquivoEmDiretorio(diretorioSaida));
    }

    @Entao("^deveria gerar arquivo contendo o seguinte resumo$")
    public void deveriaGerarArquivoContendoOSeguinteResumo(List<ResumoDataTable> resumos) throws Exception {
        ResumoDataTable resumo = resumos.get(0);

        ArquivoDataTable arquivoSaidaEsperado = new ArquivoDataTable();
        arquivoSaidaEsperado.setNome(NOME_ARQUIVO_VALIDO_DONE_DAT);
        arquivoSaidaEsperado.setDiretorio(DIRETORIO_DATA_OUT);

        assertEquals(EXPECTATIVA_QUE_O_ARQUIVO_EXISTA,
                manipuladorArquivoTestHelper.verificarSeArquivoExiste(arquivoSaidaEsperado));

        ResumoDataTable arquivoGerado = manipuladorArquivoTestHelper.lerArquivoRetornandoDataTable(arquivoSaidaEsperado);

        assertEquals(resumo.getQuantidadeClientes(), arquivoGerado.getQuantidadeClientes());
        assertEquals(resumo.getQuantidadeVendedores(), arquivoGerado.getQuantidadeVendedores());
        assertEquals(resumo.getIdVendaMaisCara(), arquivoGerado.getIdVendaMaisCara());
        assertEquals(resumo.getNomePiorVendedor(), arquivoGerado.getNomePiorVendedor());
    }

    @Entao("^deveria gerar arquivo com nome \"([^\"]*)\" na pasta \"([^\"]*)\" atraves de agendador$")
    public void deveriaGerarArquivoComNomeNaPastaAtravesDeAgendador(String nomeArquivoSaida, String diretorioSaida) throws Throwable {
        ArquivoDataTable arquivoSaidaEsperado = new ArquivoDataTable(nomeArquivoSaida, diretorioSaida);
        assertEquals(EXPECTATIVA_QUE_O_ARQUIVO_EXISTA,
                manipuladorArquivoTestHelper.verificarSeArquivoExiste(arquivoSaidaEsperado));
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

    @E("^deveria retornar mensagem de excecao arquivo sem extensao valida$")
    public void deveriaRetornarMensagemDeExcecaoArquivoSemExtensaoValida() {
        assertEquals(mensagemExceptionEsperada, mensagemExceptionRetornada);
    }

    @E("^deveria apagar arquivo original$")
    public void deveriaApagarArquivoOriginal() {
        assertEquals(EXPECTATIVA_QUE_NAO_EXISTA_ARQUIVOS,
                manipuladorArquivoTestHelper.verificarSeExisteAlgumArquivoEmDiretorio("data/in"));
    }
}