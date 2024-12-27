package test_runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/features/update_profile.feature"},
        glue = {"step_definitions"},
        plugin = {
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        })

public class TestRunner extends AbstractTestNGCucumberTests {


}





