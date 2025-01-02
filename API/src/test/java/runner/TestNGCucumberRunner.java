package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features", // Path to feature files
        glue = "stepDefinitions", // Path to step definitions
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"} // Allure report plugins
)

public class TestNGCucumberRunner extends AbstractTestNGCucumberTests{
}
