package br.com.agibank.analisadordedados;

import br.com.agibank.analisadordedados.domain.AllDomainTests;
import br.com.agibank.analisadordedados.service.AllServiceTests;
import br.com.agibank.analisadordedados.web.AllWebTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllDomainTests.class,
        AllWebTests.class,
        AllServiceTests.class
})
public class UnitTests {

}
