package br.com.agibank.analisadordedados.web.rest;

import br.com.agibank.analisadordedados.AnalisadorDeDadosApplication;
import br.com.agibank.analisadordedados.service.ArquivoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.URI_V1_API_ARQUIVOS_PROCESSAR_VENDAS;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AnalisadorDeDadosApplication.class)
@AutoConfigureMockMvc
public class ArquivoVendaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArquivoService arquivoService;

    private List<String> listaNomeArquivosProcessados;

    @Before
    public void inicializar() {
        listaNomeArquivosProcessados = new ArrayList<>();
        listaNomeArquivosProcessados.add("arquivo-001");
    }

    @Test
    public void aoProcessarVendasDeveriaRetornarNomeArquivosProcessados() throws Exception {
        BDDMockito.given(arquivoService.processar()).willReturn(listaNomeArquivosProcessados);

        ResultActions resultActions = processarArquivosVendas();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").value("arquivo-001"));
    }

    private ResultActions processarArquivosVendas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ARQUIVOS_PROCESSAR_VENDAS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }
}