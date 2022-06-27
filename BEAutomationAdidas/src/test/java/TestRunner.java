import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/petShopManaging.feature"}, glue = { "steps" }, plugin = {
				"pretty", "html:target/report-HTML.html", "json:target/report-JSON.json",
				"junit:target/report-XML.xml" })


public class TestRunner {

		
	
}
