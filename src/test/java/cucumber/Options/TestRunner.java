package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",glue= {"stepDefinitions"})
//@CucumberOptions(features="classpath:features",glue= {"stepDefinitions"},tags = "@DeletePlace")
public class TestRunner {
}
