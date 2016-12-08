package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract class AbstractPage {
	
	protected final WebDriver webdriver;
	protected final WebDriverWait wait;
	protected final WebDriverWaitFactory waitFactory;

	protected AbstractPage(WebDriver webdriver) {
		if(webdriver == null) {
			throw new IllegalArgumentException("webdriver cannot be null");
		}
		this.webdriver = webdriver;
		waitFactory = new WebDriverWaitFactory(webdriver);
		wait = waitFactory.create();
	}
}
