package com.stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "D:\\SimpliLearn\\Assignments\\Phase_2\\pizzaHut\\src\\test\\java\\com\\features\\login.feature",
		plugin = {"pretty", "html:target/cucumber-reports"},
		glue = {"com.stepDefinitions"}
		)
public class TestRunner {

}