package gentester;


import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", glue = "gentester", features = "src/test/resources/features")
public class RunCukesTest {
}
