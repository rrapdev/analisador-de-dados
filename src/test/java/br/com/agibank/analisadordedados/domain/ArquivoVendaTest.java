package br.com.agibank.analisadordedados.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(JUnit4.class)
public class ArquivoVendaTest {

    private ArquivoVenda arquivoVenda;

    private Path pathEsperado;

    @Before
    public void inicializarContexto() {
        File file = new File(DIRETORIO_RESOURCES + NOME_ARQUIVO_DAT);
        pathEsperado = file.toPath();
    }

    @Test
    public void aoChamarConstrutorPathDeveriaAtribuirNomeEhPath() {
        arquivoVenda = new ArquivoVenda(pathEsperado);

        assertEquals(NOME_ARQUIVO_DAT, arquivoVenda.getNome());
        assertSame(pathEsperado, arquivoVenda.getPath());
    }

    @Test
    public void aoChamarGetCaminhoCompletoArquivoSaidaDeveriaRetornarCaminhoCompletoArquivoSaidaEsperado() {
        arquivoVenda = new ArquivoVenda(pathEsperado);
        String retorno = arquivoVenda.getCaminhoCompletoArquivoSaida();
        assertEquals(ArquivoVenda.DIRETORIO_SAIDA +
                ArquivoVenda.SEPARADOR +
                NOME_ARQUIVO_DONE_DAT, retorno);
    }

    @Test
    public void aoSetarRelatorioDeveriaAtribuirValorEsperado() {
        RelatorioResumoVendas r = new RelatorioResumoVendas();
        arquivoVenda = new ArquivoVenda(pathEsperado);
        arquivoVenda.setRelatorioResumoVendas(r);
        assertSame(r, arquivoVenda.getRelatorioResumoVendas());
    }

    public static final String DIRETORIO_RESOURCES = "src/main/resources/";
    public static final String NOME_ARQUIVO_DAT = "arquivo.dat";
    public static final String NOME_ARQUIVO_DONE_DAT = "arquivo.done.dat";
}
