package at.fhj.swd14.pse.uitest;

import at.fhj.swd14.pse.pageobjects.LoginPage;
import at.fhj.swd14.pse.pageobjects.WelcomePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * The base class for all Selenium tests
 */
public abstract class BaseUITest {

    private static final String STUDENT1_PW = "x";
    private static final String STUDENT1 = "student1";
    private static final String LINK;

    protected static WebDriver webdriver;

    static {
        final String seleniumUrl = System.getProperty("selenium.url");
        if (seleniumUrl == null) {
            LINK = "https://localhost:8443/swd14-fe/";
        } else {
            LINK = seleniumUrl;
        }
    }

    @BeforeClass
    public static void setUp() {
        webdriver = setupWebDriver();
        webdriver.get(LINK);
    }

    private static WebDriver setupWebDriver() {
        final String driverProperty = System.getProperty("selenium.driver");
        final Driver driver;
        if (driverProperty == null) {
            driver = Driver.FIREFOX;
        } else {
            driver = Driver.valueOf(driverProperty);
        }

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        return driver.start(capabilities);
    }

    @AfterClass
    public static void tearDown() {
        if (webdriver != null) {
            webdriver.quit();
        }
    }

    /*
     * Login with default account
     * @throws IllegalArgumentException if login fails
     */
    protected static WelcomePage login() {
        return login(STUDENT1, STUDENT1_PW);
    }

    /*
     * Login
     * @param username
     * @param password
     * @throws IllegalArgumentException if login fails
     */
    protected static WelcomePage login(String username, String password) {
        LoginPage loginPage = new LoginPage(webdriver);
        WelcomePage welcomePage = loginPage.loginValidUser(username, password);
        if (!"Welcome".equals(webdriver.getTitle())) {
            throw new IllegalArgumentException("login failed");
        }
        return welcomePage;
    }

    /*
     * Go to the "Welcome" page to examine results
     */
    protected WelcomePage gotoStartPage() {
        webdriver.get(LINK);
        return new WelcomePage(webdriver);
    }

    protected enum Driver {
        FIREFOX, HTML_UNIT, CHROME, EDGE, INTERNET_EXPLORER, SAFARI;

        WebDriver start(Capabilities capabilities) {
            switch (this) {
                case FIREFOX:
                    return new FirefoxDriver(capabilities);
                case HTML_UNIT:
                    return new HtmlUnitDriver(capabilities);
                case CHROME:
                    return new ChromeDriver(capabilities);
                case EDGE:
                    return new EdgeDriver(capabilities);
                case INTERNET_EXPLORER:
                    return new InternetExplorerDriver(capabilities);
                case SAFARI:
                    return new SafariDriver(capabilities);
                default:
                    throw new AssertionError();
            }
        }
    }
}
