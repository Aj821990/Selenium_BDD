package com.hellofresh.challenge.commonUtilities;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

public class HtmlReporter {
	Robot robot;
	public static String projectName = "Hellofresh";
	public static String reportFileName = projectName+" Automation report.html";
	public static String reportPath = System.getProperty("user.dir")+"\\Screenshots";
	public static String status;

	public WebDriver webdriver;
	public Date stTime = new Date();
	public Date endate = new Date();
	//public Dictionary<String, String> Settings = null;
	private static HtmlReporter report = new HtmlReporter();

	private HtmlReporter() {

	}

	public static HtmlReporter getInstance() {
		return report;
	}

	public void reportStep(String stepName, String StepDescription,
			String status2, boolean isScreenshotReqd, String screenshotName) {
		FileWriter fstream = null;
		//String status;

		try {
			fstream = new FileWriter(reportFileName, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedWriter out = new BufferedWriter(fstream);
		try {

			if (stepName.equals("<b>Application Page</b>")) {
				out.write("<TR bgcolor=\"#99C7FF\">");
				out.write("<TD>" + stepName + "</TD><TD>" + StepDescription
						+ "</TD><TD></TD><TD></TD><TD></TD>");
			} else {
				out.write("<TR>");
				if (status2.equalsIgnoreCase("true")) {
					status = "<TD class='pass'>Passed";
				} else if (status2.equalsIgnoreCase("false")) {
					status = "<TD class='fail'>Failed";
				} else {
					status = "<TD>";
				}

				if (isScreenshotReqd) {
					String screenshot = captureScreenshotWithName(reportPath, screenshotName);
					out.write("<TD>" + stepName + "</TD><TD>" + StepDescription
							+ "</TD>" + status + "</TD>"
							+ "</TD><TD><A href=\"" + screenshot
							+ "\">View Screenshot</A></TD><TD>" + new Date()
							+ "</TD>");
				} else {
out.write("<TD>"+stepName+"</TD><TD>"+StepDescription+"</TD>"+status+"</TD>"+"</TD><TD></TD><TD>"+ new Date()+"</TD>");
				}

			}
out.write("</TR>");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}

	
	public static String captureScreenshotWithName(String uploadPath, String screenshotName) throws IOException, HeadlessException, AWTException{
	
		
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
