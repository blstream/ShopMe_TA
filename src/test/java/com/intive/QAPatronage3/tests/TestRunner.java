package com.intive.QAPatronage3.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "feature",
        tags = {"@test1,@test2,@test3"}
)
public class TestRunner {
}
