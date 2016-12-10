package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage extends AbstractPage {

	private final By profileLink = By.linkText("Profile");
	private final By usersLink = By.linkText("Users");

	public WelcomePage(WebDriver webdriver) {
		super(webdriver);
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
