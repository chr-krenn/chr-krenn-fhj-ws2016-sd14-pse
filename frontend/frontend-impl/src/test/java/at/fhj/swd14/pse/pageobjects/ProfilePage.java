package at.fhj.swd14.pse.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends AbstractPage {

	private static final By EMAILSECTION = By.xpath("//h3[contains(text(), 'Mail Adressen')]/following-sibling::*[1]");
	private static final By HOBBYSECTION = By.xpath("//h3[contains(text(), 'Hobbies')]/following-sibling::*[1]");
	private static final By SKILLSECTION = By.xpath("//h3[contains(text(), 'Fähigkeiten')]/following-sibling::*[1]");
	private static final By TELNRSECTION = By.xpath("//h3[contains(text(), 'Telefonnummern')]/following-sibling::*[1]");

	public ProfilePage(WebDriver webdriver) {
		super(webdriver);
	}
	
	public boolean addEmail(String mail) {
		return addTextToSection(mail, EMAILSECTION);
	}
	
	public boolean removeEmail(String mail) {
		return removeTextFromSection(mail, EMAILSECTION);
	}
	
	public boolean addHobby(String hobby) {
		return addTextToSection(hobby, HOBBYSECTION);
	}
	
	public boolean removeHobby(String hobby) {
		return removeTextFromSection(hobby, HOBBYSECTION);
	}
	
	public boolean addSkill(String skill) {
		return addTextToSection(skill, SKILLSECTION);
	}
	
	public boolean removeSkill(String skill) {
		return removeTextFromSection(skill, SKILLSECTION);
	}
	
	public boolean addTelNr(String telnr) {
		return addTextToSection(telnr, TELNRSECTION);
	}
	
	public boolean removeTelNr(String telnr) {
		return removeTextFromSection(telnr, TELNRSECTION);
	}
	
	private boolean addTextToSection(String text, By section) {
		WebElement emailContainer = findSection(section);
	 	WebElement input = emailContainer.findElement(By.tagName("input"));
	 	input.sendKeys(text);
	 	List<WebElement> buttons = getButtonFromContainer(emailContainer);
	 	WebElement button = buttons.get(buttons.size()-1);
	 	button.click();
	 	wait.until(ExpectedConditions.presenceOfElementLocated(getInputField(text)));
	 	return getSectionText(section).contains(text);
	}
	
	private boolean removeTextFromSection(String text, By section) {
		WebElement emailContainer = findSection(section);
		List<WebElement> buttons = getButtonFromContainer(emailContainer);
	 	WebElement button = buttons.get(buttons.size()-2);
	 	wait.until(ExpectedConditions.elementToBeClickable(button));
	 	((JavascriptExecutor)webdriver).executeScript("arguments[0].click();", button);
	 	wait.until(ExpectedConditions.presenceOfElementLocated(section));
	 	return !getSectionText(section).contains(text);
	}

	private String getSectionText(By section) {
		return findSection(section).getAttribute("innerHTML");
	}
	
	private By getInputField(String text) {
		return By.cssSelector("input[value='" + text + "']");
	}

	private WebElement findSection(By section) {
		return webdriver.findElement(section);
	}

	private List<WebElement> getButtonFromContainer(WebElement container) {
		List<WebElement> buttons = container.findElements(By.tagName("button"));
		return buttons;
	}
}
