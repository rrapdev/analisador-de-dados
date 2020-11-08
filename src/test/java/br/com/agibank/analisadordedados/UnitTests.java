package br.com.agibank.analisadordedados;

import br.com.agibank.analisadordedados.application.ApplicationTest;
import br.com.agibank.analisadordedados.domain.AllDomainTests;
import br.com.agibank.analisadordedados.service.AllServiceTests;
import br.com.agibank.analisadordedados.task.ProcessadorArquivosTaskTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllDomainTests.class,
        AllServiceTests.class,
        ProcessadorArquivosTaskTest.class,
        ApplicationTest.class
})
public class UnitTests {

}
