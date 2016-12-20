package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

class WebDriverWaitFactory {

	private static final short DEFAULT = 10;
	private final WebDriver webdriver;

	public WebDriverWaitFactory(WebDriver webdriver) {
		this.webdriver = webdriver;
		this.create(22);
	}

	public WebDriverWait create() {
		return new WebDriverWait(webdriver, DEFAULT);
	}
	
	public WebDriverWait create(long seconds) {
		return new WebDriverWait(webdriver, seconds);
	}
}
