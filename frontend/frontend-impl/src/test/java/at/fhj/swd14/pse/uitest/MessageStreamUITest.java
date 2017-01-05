package at.fhj.swd14.pse.uitest;

import at.fhj.swd14.pse.pageobjects.WelcomePage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MessageStreamUITest extends BaseUITest {
	private final static String TestTitle = "TestMessageTitle";
	private final static String TestContent = "TestMessageContent";
	private final static String TestCommentContent = "TestMessageCommentContent";
	
    @BeforeClass
    public static void setup() {
        login();
    }

    @Test
    public void testPostMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
    }
    
    @Test
    public void testLikeMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        welcomePage.toggleLikeFirstMessage();
        Assert.assertEquals(1, welcomePage.getFirstMessageLikeCount());
        welcomePage.toggleLikeFirstMessage();
        Assert.assertEquals(0, welcomePage.getFirstMessageLikeCount());
    }
    
    @Test
    public void testAddCommentToMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        welcomePage.submitCommentToFirstMessage();
        welcomePage = getWelcomePage();
        Assert.assertEquals(1, welcomePage.getFirstMessageCommentCount());
    }
    
    @Test
    public void testLikeCommentOfMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        welcomePage.submitCommentToFirstMessage();
        welcomePage = getWelcomePage();
        Assert.assertEquals(1, welcomePage.getFirstMessageCommentCount());
        
        Assert.assertEquals(0, welcomePage.getFirstMessageFirstCommentLikeCount());
        welcomePage.toggleLikeFirstMessageFirstComment();
        Assert.assertEquals(1, welcomePage.getFirstMessageFirstCommentLikeCount());
        welcomePage.toggleLikeFirstMessageFirstComment();
        Assert.assertEquals(0, welcomePage.getFirstMessageFirstCommentLikeCount());
    }
    
    @Test
    public void testAddManyCommentsAndLoadMoreToMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        for (int i = 0; i < 15; i++) {
        	welcomePage.submitCommentToFirstMessage();
		}
        welcomePage = getWelcomePage();
        Assert.assertEquals(10, welcomePage.getFirstMessageCommentCount());
        welcomePage.firstMessageLoadMoreComments();
        Assert.assertEquals(15, welcomePage.getFirstMessageCommentCount());
    }
    
    private WelcomePage getWelcomePage(){
    	WelcomePage welcomePage = gotoStartPage();
        boolean isWelcomePage = webdriver.getTitle().toLowerCase().contains("welcome");
        Assert.assertTrue(isWelcomePage);
        return welcomePage;
    }
    
    private WelcomePage InsertNewMessage(WelcomePage welcomePage){
    	welcomePage.insertNewMessage(TestTitle, TestContent);
        welcomePage = getWelcomePage();
        Assert.assertEquals(TestTitle, welcomePage.getFirstMessageTitle());
        Assert.assertEquals(TestContent, welcomePage.getFirstMessageContent());
        Assert.assertEquals(0, welcomePage.getFirstMessageLikeCount());
        return welcomePage;
    }
}
