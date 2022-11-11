package com.n11.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(publish = true,
        plugin = {"pretty","html:target/cucumber-reports"},//Type "cucumber" in terminal to get reports of test
        features = "src/test/resources/features/",//path to access feature files
        glue = "com/n11/step_definitions",
        tags = "@campaigns"//scenario name
)

public class Runner { }
