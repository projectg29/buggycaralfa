package org.buggy.util;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

	public WebDriver driver;

	public Waits(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForElement(WebElement element) {

		waitForElement(element, 15);
	}

	public void waitForElement(WebElement element, int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, seconds);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {

			Assert.fail("Element not found on page: " + element);
		}
	}

	public void waitForElements(List<WebElement> elementList) {

		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
		} catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
			Assert.fail("Element not found on page: " + elementList);
		}
	}

	
}
