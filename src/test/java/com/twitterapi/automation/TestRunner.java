//rename class to TestRunner to run it with maven

package com.twitterapi.automation;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/com/twitterapi/automation/features",
		glue={"com.twitterapi.automation"},
		tags={"@run"},
		plugin={"pretty", "json:target/cucumber-reports/cucumber.json"}
		)
public class TestRunner{
}
