import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = { "pretty", "html:target/html/",
		"json:target/json/cucumber.json" }, features = "src/test/features", strict = true)
public class CucumberRunnerTest {

}
