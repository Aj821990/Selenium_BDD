package com.hellofresh.challenge.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;




	@RunWith(Cucumber.class)
	@CucumberOptions(
	 features= "src/test/resources/Features",
	 glue= {"com.hellofresh.challenge.stepDefinition"},
	 plugin= {"html:target/cucumber-html-report"},
	 tags= {"@Register"},
	 monochrome= true,
	dryRun= false,
	strict= true
	 
	 )
	 
	public class TestRunner {
	 
		
		 
		
		
	}


