package com.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/Features",
		glue="stepDefination",
		plugin={"pretty","html:target/reports.html"},
		monochrome=true,
		dryRun=false
		)
public class TestRunner {

	
}
