package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;

@CucumberOptions(features = "src/test/java/features/paymentpage.feature",
glue = {"steps","hooks"},
snippets= SnippetType.CAMELCASE,
monochrome = true)
public class Runner extends AbstractTestNGCucumberTests{
	

}
