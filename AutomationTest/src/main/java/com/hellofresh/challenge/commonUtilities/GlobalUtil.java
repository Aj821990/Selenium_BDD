package com.hellofresh.challenge.commonUtilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GlobalUtil {


	public static Properties prop = new Properties();
	public static HtmlReporter htmlReporter = HtmlReporter.getInstance();
	public static WebDriver driver;
	private static GlobalUtil globalUtil = new GlobalUtil();
	public String configFileName = System.getProperty("user.dir")+"\\Configuration\\config.properties";
	public static Logger logger;
	static String uploadPath = System.getProperty("user.dir")+"\\Screeenshots";
	
	
	public static GlobalUtil getInstance(){
		return globalUtil;
	}
	
	private GlobalUtil(){
		
		try {
			FileInputStream inputStream = new FileInputStream(configFileName);
				prop.load(inputStream);
				
				
			/*	if(prop.get("isHTMLReport").equals("true")){
					String reportTitle = prop.getProperty("reportTitle");
				//	String reportPath = System.getProperty("user.dir")+"";
					
					
				}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	public static Logger getLogger(){
		logger = Logger.getLogger("AutomationTest");
		  PropertyConfigurator.configure("log4j.properties");
		return logger;
	}
	public String getValue(String key){
		return prop.getProperty(key).toString();
	}
	
	public static void reporter(String stepName,String StepDescription,String status2,boolean isScreenshotReqd,String screenshotName){
	htmlReporter.reportStep(stepName, StepDescription, status2, isScreenshotReqd, screenshotName);
	}
	
	@SuppressWarnings("deprecation")
	public void loadDriver(){
		String browserName = getValue("browserType");
		if("InternetExplorer".equalsIgnoreCase(browserName)){
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
		}
		else if("Chrome".equalsIgnoreCase(browserName)){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver  = new ChromeDriver();
		}
		
		else if("Mozilla".equalsIgnoreCase(browserName)){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver  = new FirefoxDriver();
		}
		
		else{
			System.out.println("driver info not present");
		}
		
	}
	
	public WebDriver getDriver(){
		return driver;
	}
	
/*	public void getEnvironment(){
		String env = getValue("environment");
		if("SIT".equalsIgnoreCase(env)){
			loadUrl();
		}
		else if("UAT".equalsIgnoreCase(env)){
			loadUrl();
		}
		else{
			System.out.println("environment details not present");
		}
	}*/
	
	
	public void getApplicationURL(){
		String env = getValue("environment");
		
		
		if("SIT".equalsIgnoreCase(env)){
			driver.get(getValue("siturl"));
			maximizeWindow();
		}
		
		else if("UAT".equalsIgnoreCase(env)){
			driver.get(getValue("uaturl"));
			maximizeWindow();
		}
		else{
			System.out.println("environment details not present");
		}
		
	}
	
	public void maximizeWindow(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	public void taskKill(){
		String browserName = getValue("browserType");
		 try {
			 
			 if("InternetExplorer".equalsIgnoreCase(browserName)){
				   Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				}
				else if("Chrome".equalsIgnoreCase(browserName)){
					Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");	
				}
				
				else if("Mozilla".equalsIgnoreCase(browserName)){
					  Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
					
				}
				
				else{
					System.out.println("browser process did not killed");
				}
				
			
		 }
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
	
}
	public void selectByValue(WebElement drpdownelement, String value){
	    Select sel = new Select(drpdownelement);
		sel.selectByValue(value);
	}
	
	public void selectByVisibleText(WebElement visibleElement, String visibleTxt){
	    Select sel = new Select(visibleElement);
		sel.selectByVisibleText(visibleTxt);
	}
	
	public void explicitWait(By webElement, int time ){
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
	
	}
	
	public String dateFormat(String val) throws ParseException{
		SimpleDateFormat sdfSource = new SimpleDateFormat("dd/MM/yyyy");
		
		 Date date = sdfSource.parse(val);
		String format = sdfSource.format(date);
		 return format;
	}
	
	public	Map<String, String> splitDate(String iSplit) throws ParseException{
		String birthDay = dateFormat(iSplit);
		String[] dayFormat = birthDay.split("/");
		String dd = dayFormat[0];
		String month = dayFormat[1];
		String strpattern = "^0+";
		String trimMonth = month.replaceAll(strpattern,"");
		System.out.println("trim val is:"+trimMonth);
		String year = dayFormat[2];
				
		Map<String , String> datemap = new HashMap<String, String>();
		datemap.put("date", dd);
		datemap.put("month", trimMonth);
		datemap.put("year",year);
		System.out.println(datemap);
		return datemap;
		
	}
	
	public static String captureScreenshotWithName(String screenshotName) throws IOException, HeadlessException, AWTException{
		
		
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	File file = new File(uploadPath+"\\images");
	if(!file.exists()){
		file.mkdir();
	}
	ImageIO.write(image, "png", new File(uploadPath+"\\images\\"+screenshotName+".png"));
	System.setProperty("org.uncommons.reportng.escape-output", "false");
	String screenshot = "./Screenshots/images/"+screenshotName+".png";
	return screenshot;

	
}

}