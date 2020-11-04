package br.com.agibank.analisadordedados;

import br.com.agibank.analisadordedados.cucumber.CucumberTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UnitTests.class,
        CucumberTest.class
})
public class AllTests {
}
