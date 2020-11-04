package br.com.agibank.analisadordedados.cucumber.stepdefs;

import br.com.agibank.analisadordedados.AnalisadorDeDadosApplication;
import br.com.agibank.analisadordedados.util.ManipuladorArquivoTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

@WebAppConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {AnalisadorDeDadosApplication.class})
public class Stepdefs {

    @Autowired
    protected ManipuladorArquivoTestHelper manipuladorArquivoTestHelper;

    protected static ResultActions resultado;

}
