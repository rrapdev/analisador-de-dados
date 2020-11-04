package br.com.agibank.analisadordedados.cucumber.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.agibank.analisadordedados.util.ConstantesUtil.URI_V1_API_ARQUIVOS_PROCESSAR_VENDAS;

@Component
public class ProcessadorArquivosGateway {
    @Autowired
    private MockMvc mockMvc;

    public ResultActions processarArquivosVendas() throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(URI_V1_API_ARQUIVOS_PROCESSAR_VENDAS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
    }
}