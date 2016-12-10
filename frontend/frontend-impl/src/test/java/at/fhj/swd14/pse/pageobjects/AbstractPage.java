package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is the base class for all page objects
 */
abstract class AbstractPage {
	
	protected final WebDriver webdriver;
	
	/*
	 * only use this field to wait for certain conditions using explicit waits!
	 * DO NOT mix implicit and explicit waits, cause this can result in 
	 * unpredictable behavior
	 */
	protected final WebDriverWait wait;
	protected final WebDriverWaitFactory waitFactory;

	protected AbstractPage(WebDriver webdriver) {
		if(webdriver == null) {
			throw new IllegalArgumentException("webdriver cannot be null");
		}
		this.webdriver = webdriver;
		waitFactory = new WebDriverWaitFactory(webdriver);
		wait = waitFactory.create();
		waitForReadyDom(webdriver);
	}

	/*
	 * wait for page to be completely loaded using explicit wait
	 * @param the webdriver
	 */
	private void waitForReadyDom(WebDriver webdriver) {
		wait.until(new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver wdriver) {
	            return ((JavascriptExecutor) webdriver).executeScript(
	                "return document.readyState"
	            ).equals("complete");
	        }
	    });
	}
}
