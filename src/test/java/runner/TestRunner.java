package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features="src/test/resource/features",
		glue={"stepdefinitions","hooks"},
	//	tags="@Slider",
		tags="@all",				
		dryRun=false,
		plugin={"pretty",
				        "html:target/CucumberReports/CucumberReport.html",
				         "json:target/CucumberReports/CucumberReport.json",
				         "junit:target/CucumberReports/CucumberReport.xml"
				       }
        )
public class TestRunner extends AbstractTestNGCucumberTests{

}
