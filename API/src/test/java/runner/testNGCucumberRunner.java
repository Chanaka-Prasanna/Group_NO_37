package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features", // Path to feature files
        glue = "stepDefinitions", // Path to step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"} // Reporting plugins
)

public class testNGCucumberRunner extends AbstractTestNGCucumberTests{
}
