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
public class TestRunner{}

