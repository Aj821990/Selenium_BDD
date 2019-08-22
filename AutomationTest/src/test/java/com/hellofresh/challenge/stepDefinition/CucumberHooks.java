package com.hellofresh.challenge.stepDefinition;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.hellofresh.challenge.commonUtilities.GlobalUtil;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class CucumberHooks {
	public static Logger logger;
	 GlobalUtil globalutil = GlobalUtil.getInstance();
	
	@Before
	@Given("^launch browser$")
	public void launch_browser() throws Throwable {
		 logger = Logger.getLogger("AutomationTest");
		  PropertyConfigurator.configure("log4j.properties");
		  globalutil.loadDriver();
		  logger.info("browser is opened");
		  globalutil.getApplicationURL();
		  logger.info("URL is launched");
		  GlobalUtil.reporter("Step1", "Test check", "true", true, "scren1");
	    
	}
	
	/*@After
	@Then("^close browser and kill all browser instance$")
	public void close_browser_and_kill_all_browser_instance() throws Throwable {
		 globalutil.getDriver().quit();
		 globalutil.taskKill();
		
	}*/


	
}
