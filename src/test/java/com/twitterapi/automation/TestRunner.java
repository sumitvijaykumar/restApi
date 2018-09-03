//rename class to TestRunner to run it with maven

package com.twitterapi.automation;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features="src/test/java/com/twitterapi/automation/features",
		glue={"com.twitterapi.automation.stepfiles"},
		tags={"@run"},
		plugin={"pretty"}
		)
public class TestRunner{
}
