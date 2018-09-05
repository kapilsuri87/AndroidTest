package org.framework.adib.selendroidTestApp.Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/org/framework/adib/selendroidTestApp/Feature/MyApplicationFeature.feature" }, 
                        glue = {"org/framework/adib/selendroidTestApp/StepDefinition" }, 
                        plugin = {"html:target/cucumber-html-report" }, 
                        tags= {"@Smoke"},
                        dryRun = false, monochrome = false)

public class TestRunner {
}

// ORed : tags = {"@Smoke, @Regression"} To add condition for OR operator
// Anded : tags = {"@Smoke", "@Regression"} To add condition for AND operator
// Ignore Case : tags = {"~@Smoke", "@Regression"} To ignore a set of test cases
// use ~ operator