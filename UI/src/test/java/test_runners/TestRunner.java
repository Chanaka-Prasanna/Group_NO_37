package test_runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features= {"src/test/resources/features"},glue = {"step_definitions"},plugin = {"html:target/htmlreport.html"})
public class TestRunner extends AbstractTestNGCucumberTests {

}





