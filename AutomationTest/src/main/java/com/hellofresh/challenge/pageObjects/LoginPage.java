package com.hellofresh.challenge.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

	 WebDriver ldriver;
	 
	 public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		 
	}
	
		 
	 @FindBy(id="email")
	 WebElement txtemail;
	 
	 
	 @FindBy(id="passwd")
	 WebElement txtpassword;
	 
	 @FindBy(id="SubmitLogin")
	 WebElement txtsignIn;
	 
	 
	 public void setEmailId(String uEmail){
		 txtemail.sendKeys(uEmail);
	 }
	 
	 public void setPassword(String uPassword){
		 txtpassword.sendKeys(uPassword);
	 }
	 
	 public void clickSubmit(){
		 txtsignIn.click();
	 }
	 
	 /*@Test
	    public void logInTest() {
	        String fullName = "Joe Black";
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
	        driver.findElement(By.id("email")).sendKeys(existingUserEmail);
	        driver.findElement(By.id("passwd")).sendKeys(existingUserPassword);
	        driver.findElement(By.id("SubmitLogin")).click();
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

	        assertEquals("MY ACCOUNT", heading.getText());
	        assertEquals(fullName, driver.findElement(By.className("account")).getText());
	        assertTrue(driver.findElement(By.className("info-account")).getText().contains("Welcome to your account."));
	        assertTrue(driver.findElement(By.className("logout")).isDisplayed());
	        assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	    }*/
	 
	 
}
