package org.buggy.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.buggy.pageobjects.HomePage;
import org.buggy.pageobjects.RegisterationPage;
import org.buggy.util.Utils;
import org.buggy.util.ReadFileData;
import org.buggy.util.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitons {

	Properties properties = ReadFileData.getProperty();
	public WebDriver driver;
	private static String login = "";
	Utils utils = new Utils();

	public void startDriver(String url) {

		System.setProperty(properties.getProperty("web.driver"),
				new File(properties.getProperty("browser.executable")).getPath());
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {

		if (scenario.isFailed()) {

			utils.addScreenshot(scenario, driver);
		}

		driver.quit();

	}

	@Given("User is on home page")
	public void user_is_on_home_page() {
		HomePage homepage = new HomePage(driver);
		String url = properties.getProperty("URL");
		startDriver(url);

	}

	@When("User goto register page")
	public HomePage user_goto_register_page() {
		HomePage homepage = new HomePage(driver);
		Waits waits = new Waits(driver);
		homepage.registerButton.click();
		return homepage;
	}

	@Then("User should be able to register successfully")
	public void user_should_be_able_to_register_successfully() throws Throwable {
		RegisterationPage registerpage = new RegisterationPage(driver);
		Waits waits = new Waits(driver);
		String loginName = utils.getRandomUserName();
		System.out.println(" loginName " + loginName);
		waits.waitForElement(registerpage.inputLogin);
		registerpage.inputLogin.sendKeys(loginName);
		registerpage.inputFirstname.sendKeys("Bala");
		registerpage.inputLastname.sendKeys("Mithran");
		registerpage.inputPassword.sendKeys("Admin@123");
		registerpage.inputConfirmPassword.sendKeys("Admin@123");
		login = registerpage.inputLogin.getText();
		registerpage.registerSubmitButton.click();
		waits.waitForElement(registerpage.registerSuccsessfulMessage);
		registerpage.registerSuccsessfulMessage.isDisplayed();
		login = loginName;

	}

	@When("User enters credentials {string} {string}")
	public void user_enters_credentials(String username, String password) {
		HomePage homepage = new HomePage(driver);
		Waits waits = new Waits(driver);
		waits.waitForElement(homepage.homePageLabel);
		homepage.inputLogin.sendKeys(properties.getProperty("username"));
		homepage.inputPassword.sendKeys(properties.getProperty("password"));
		homepage.loginButton.click();
	}

	@Then("User should be able to see logout button")
	public void user_should_be_able_to_see_logout_button() {
		HomePage homepage = new HomePage(driver);
		Waits waits = new Waits(driver);
		waits.waitForElement(homepage.logoutButton);

	}

}
