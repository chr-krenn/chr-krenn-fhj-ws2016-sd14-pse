package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

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

    protected AbstractPage(WebDriver webdriver, String pagename) {
        if (webdriver == null) {
            throw new IllegalArgumentException("webdriver cannot be null");
        }
        this.webdriver = webdriver;
        waitFactory = new WebDriverWaitFactory(webdriver);
        wait = waitFactory.create();
        waitForReadyDom(webdriver);
        assertTrue("Pagename \"" + pagename + "\" was not found in actual title \"" + webdriver.getTitle() + "\"",
                webdriver.getTitle().toLowerCase().contains(pagename.toLowerCase()));
    }

    /*
     * wait for page to be completely loaded using explicit wait
     * @param the webdriver
     */
    private void waitForReadyDom(WebDriver webdriver) {
        wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) webdriver).executeScript(
                "return document.readyState"
        ).equals("complete"));
    }
}
