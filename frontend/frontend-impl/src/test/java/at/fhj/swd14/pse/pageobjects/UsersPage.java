package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class UsersPage extends AbstractPage {

	private static final String USERLISTSTRING = "//form[@id='personListForm']/li";
	private static final String ENTFERNEN = "Entfernen";
	private static final String FREUND_HINZUFUEGEN = "Freund hinzufügen";

	protected UsersPage(WebDriver webdriver) {
		super(webdriver, "user");
	}

	public int retrieveUserListCount() {
		return retrieveUserList().size();
	}

	public Boolean addUserAsFriend() {
		return clickFriendButton(ENTFERNEN);
	}

	public Boolean removeUserAsFriend() {
		return clickFriendButton(FREUND_HINZUFUEGEN);
	}

	public ProfilePage clickUserLink() {
		WebElement userLink = webdriver.findElement(By.xpath(USERLISTSTRING + "[1]/a"));
		userLink.click();
		return new ProfilePage(webdriver);
	}

	private boolean clickFriendButton(String expectedSpanString) {
		String xpathString = USERLISTSTRING + "[1]/button[1]";
		WebElement button = webdriver.findElement(By.xpath(xpathString));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		((JavascriptExecutor)webdriver).executeScript("arguments[0].click();", button);

		By xpath = By.xpath(xpathString + "/span");
		wait.until(ExpectedConditions.textToBePresentInElementLocated(xpath, expectedSpanString));
		return expectedSpanString.equals(webdriver.findElement(xpath).getText());
	}

	private List<WebElement> retrieveUserList() {
		return webdriver.findElements(By.xpath(USERLISTSTRING));
	}
}
