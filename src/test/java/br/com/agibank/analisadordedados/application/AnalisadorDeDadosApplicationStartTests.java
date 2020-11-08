package br.com.agibank.analisadordedados.application;

import br.com.agibank.analisadordedados.AnalisadorDeDadosApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AnalisadorDeDadosApplicationStartTests {

    @Test
    public void deveriaIniciarAhAplicacao() {
        AnalisadorDeDadosApplication.main(new String[] {});
    }

}