package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WelcomePage extends AbstractPage {

	private static final String TITLE = "Welcome";
	private final By profileLink = By.linkText("Profile");
	private final By usersLink = By.linkText("Users");

	public WelcomePage(WebDriver webdriver) {
		super(webdriver);
		wait.until(ExpectedConditions.titleContains(TITLE));
	}

    public ProfilePage changeToOwnProfilePage() {
    	webdriver.findElement(profileLink).click();
    	return new ProfilePage(webdriver);
    }
    
    public UsersPage changeToUsersPage() {
    	webdriver.findElement(usersLink).click();
    	return new UsersPage(webdriver);
    }
}
