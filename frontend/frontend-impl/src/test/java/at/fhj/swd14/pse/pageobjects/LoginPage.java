package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

	private final By usernameField = By.id("j_username");
	private final By passwordField = By.id("j_password");
	private final By loginButton = By.id("submit");

	public LoginPage(WebDriver webdriver) {
		super(webdriver);
	}

    public WelcomePage loginValidUser(String username, String password) {
    	webdriver.findElement(usernameField).sendKeys(username);
        webdriver.findElement(passwordField).sendKeys(password);
        webdriver.findElement(loginButton).submit();
        return new WelcomePage(webdriver);
    }
}