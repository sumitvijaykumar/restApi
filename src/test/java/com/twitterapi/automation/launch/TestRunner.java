//rename class to TestRunner to run it with maven

package com.twitterapi.automation.launch;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features="src/test/java/com/twitterapi/automation/launch",
		glue={"com.twitterapi.automation.launch"},
		tags={"@run"},
		plugin={"pretty"}
		)
public class TestRunner{
	/*private TestNGCucumberRunner testNGCucumberRunner;
	@BeforeClass
	public void setUpClass(){
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		setSoftAssert();
		getRequestSpecification();
		getResponseSpecification();
	}
	
	@Test(groups = "cucumber", description = "Cucumber feature file runner", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature){
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}
	
	@DataProvider
	public Object[][] features(){
		return testNGCucumberRunner.provideFeatures();
	}
	
	@BeforeTest(alwaysRun=true)
	public void beforeTest(){
		
	}
	
	@AfterTest(alwaysRun=true)
	public void afterTest(){
	}
	
	@After
	public void tearDownClass(){
		System.out.println("After");
		softAssert.assertAll();
	}
*/
}
