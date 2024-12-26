package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features", // Path to your feature files
        glue = "stepDefinitions" // Path to your step definitions
        //plugin = {"pretty", "html:target/cucumber-reports.html"} // Reporting plugins
)

public class testNGCucumberRunner extends AbstractTestNGCucumberTests{
}
