
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "your.package.path.stepDefinitions",
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class CucumberTest {

}
