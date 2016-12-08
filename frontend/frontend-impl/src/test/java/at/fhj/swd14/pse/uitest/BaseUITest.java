package at.fhj.swd14.pse.uitest;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import at.fhj.swd14.pse.pageobjects.*;

public abstract class BaseUITest {
	
	private static final String STUDENT1_PW = "x";
	private static final String STUDENT1 = "student1";
	private static boolean isLoggedIn;
	private static final String LINK = "https://localhost:8443/swd14-fe/";
	
	protected static WebDriver webdriver;
	
	@BeforeClass
	public static void setUp() {
		webdriver = new FirefoxDriver();
		webdriver.get(LINK);
	}
	
	@Before
	public void setLogin() {
		if (!isLoggedIn) {
			login();
			Assert.assertEquals("Welcome", webdriver.getTitle());
			isLoggedIn = true;
		}
	}
	
	@AfterClass
	public static void tearDown() {
		if (webdriver != null) {
			webdriver.quit();
		}
	}
	
	protected WelcomePage login() {
		return login(STUDENT1, STUDENT1_PW);
	}
	
	protected WelcomePage login(String username, String password) {
		LoginPage loginPage = new LoginPage(webdriver);
		WelcomePage welcomePage = loginPage.loginValidUser(username, password);
		return welcomePage;
	}
	
	protected WelcomePage gotoStartPage() {
		webdriver.get(LINK);
		return new WelcomePage(webdriver);
	}
}
