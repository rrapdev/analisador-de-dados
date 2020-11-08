package br.com.agibank.analisadordedados.service.validador.impl;

import br.com.agibank.analisadordedados.cucumber.datatable.ArquivoDataTable;
import br.com.agibank.analisadordedados.service.validador.ValidadorPath;
import br.com.agibank.analisadordedados.util.ManipuladorArquivoTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Path;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidadorPathImplTest {

    ValidadorPath validadorPathImpl;

    ManipuladorArquivoTestHelper manipuladorArquivoTestHelper;

    @Before
    public void inicializarContexto() {
        validadorPathImpl = new ValidadorPathImpl();
        manipuladorArquivoTestHelper = new ManipuladorArquivoTestHelper();
    }

    @Test
    public void aoSerificarSePathCorrespondeAhUmArquivoComPathCorrespondenteAhUmArquivoDeveriaRetornarTrue() {
        ArquivoDataTable arquivoData = new ArquivoDataTable();
        arquivoData.setNome("arquivo.txt");
        arquivoData.setDiretorio("data/in");
        manipuladorArquivoTestHelper.criarArquivo(arquivoData);

        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS + "/" + arquivoData.getNome());
        boolean retorno = validadorPathImpl.verificarSePathCorrespondeAhUmArquivo(path);
        assertTrue(retorno);
    }

    @Test
    public void aoSerificarSePathCorrespondeAhUmArquivoComPathCorrespondenteAhUmDiretorioDeveriaRetornarFalse() {
        Path path = Path.of(CAMINHO_DIRETORIO_PADRAO_ENTRADA_DADOS);
        boolean retorno = validadorPathImpl.verificarSePathCorrespondeAhUmArquivo(path);
        assertFalse(retorno);
    }
}