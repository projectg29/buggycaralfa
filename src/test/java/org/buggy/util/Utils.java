package org.buggy.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import io.cucumber.java.Scenario;

public class Utils {

	public void addScreenshot(Scenario scenario, WebDriver driver) throws IOException {
		String nameScreenshot = "screenshot_" + createScreenshotFileName();
		String targetPath = new File("..\\buggycarsceenshots\\output\\" + nameScreenshot).getCanonicalPath();

		if (scenario.isFailed()) {
			try {
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png", nameScreenshot);
				try {
					File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					org.apache.commons.io.FileUtils.copyFile(screenshotFile, new File(targetPath));
				} catch (IOException e) {
					System.out.println("Error Error Error");
				}
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}

	public String createScreenshotFileName() {

		final StringBuilder sb = new StringBuilder();
		final DateFormat dateformat = new SimpleDateFormat("yyyyMMdd-HHmmss");
		final java.lang.String timestamp = dateformat.format(new Date());
		sb.append(timestamp).append(".png");
		return sb.toString();

	}

	public String getRandomUserName() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 12) {
			int index = (int) (rnd.nextFloat() * characters.length());
			salt.append(characters.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

}
