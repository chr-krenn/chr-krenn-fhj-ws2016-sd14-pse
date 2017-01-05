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
        welcomePage.ToggleLikeFirstMessage();
        Assert.assertEquals(1, welcomePage.GetFirstMessageLikeCount());
        welcomePage.ToggleLikeFirstMessage();
        Assert.assertEquals(0, welcomePage.GetFirstMessageLikeCount());
    }
    
    @Test
    public void testAddCommentToMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.GetFirstMessageCommentCount());
        welcomePage.AddCommentToFirstMessage(TestCommentContent);
        welcomePage.SubmitCommentToFirstMessage();
        Wait();
        welcomePage = getWelcomePage();
        Assert.assertEquals(1, welcomePage.GetFirstMessageCommentCount());
    }
    
    @Test
    public void testLikeCommentOfMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.GetFirstMessageCommentCount());
        welcomePage.AddCommentToFirstMessage(TestCommentContent);
        welcomePage.SubmitCommentToFirstMessage();
        Wait();
        welcomePage = getWelcomePage();
        Assert.assertEquals(1, welcomePage.GetFirstMessageCommentCount());
        
        Assert.assertEquals(0, welcomePage.GetFirstMessageFirstCommentLikeCount());
        welcomePage.ToggleLikeFirstMessageFirstComment();
        Assert.assertEquals(1, welcomePage.GetFirstMessageFirstCommentLikeCount());
        welcomePage.ToggleLikeFirstMessageFirstComment();
        Assert.assertEquals(0, welcomePage.GetFirstMessageFirstCommentLikeCount());
    }
    
    @Test
    public void testAddManyCommentsAndLoadMoreToMessage() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        Assert.assertEquals(0, welcomePage.GetFirstMessageCommentCount());
        welcomePage.AddCommentToFirstMessage(TestCommentContent);
        for (int i = 0; i < 15; i++) {
        	welcomePage.SubmitCommentToFirstMessage();
        	Wait();
		}
        welcomePage = getWelcomePage();
        Assert.assertEquals(10, welcomePage.GetFirstMessageCommentCount());
        welcomePage.FirstMessageLoadMoreComments();
        Wait();
        Assert.assertEquals(15, welcomePage.GetFirstMessageCommentCount());
    }
    
    @Test
    public void testChangeCommunityFilter() {
        WelcomePage welcomePage = getWelcomePage();
        InsertNewMessage(welcomePage);
        welcomePage.CollapseCommunityDropdown();
        Wait();
        WebElement privateCommunity = welcomePage.GetPrivateCommunity();
        Assert.assertEquals("Private", privateCommunity.getText());
        privateCommunity.click();
        Wait();
        Assert.assertNotEquals(TestTitle, welcomePage.GetFirstMessageTitle());
        Assert.assertNotEquals(TestContent, welcomePage.GetFirstMessageContent());
        welcomePage.CollapseCommunityDropdown();
        WebElement allCommunity = welcomePage.GetAllCommunity();
        Assert.assertEquals("Alle", allCommunity.getText());
        allCommunity.click();
        Wait();
        Assert.assertEquals(TestTitle, welcomePage.GetFirstMessageTitle());
        Assert.assertEquals(TestContent, welcomePage.GetFirstMessageContent());
    }
    
    private WelcomePage getWelcomePage(){
    	WelcomePage welcomePage = gotoStartPage();
        boolean isWelcomePage = webdriver.getTitle().toLowerCase().contains("welcome");
        Assert.assertTrue(isWelcomePage);
        return welcomePage;
    }
    
    private WelcomePage InsertNewMessage(WelcomePage welcomePage){
    	welcomePage.InsertNewMessage(TestTitle, TestContent);
        welcomePage = getWelcomePage();
        Assert.assertEquals(TestTitle, welcomePage.GetFirstMessageTitle());
        Assert.assertEquals(TestContent, welcomePage.GetFirstMessageContent());
        Assert.assertEquals(0, welcomePage.GetFirstMessageLikeCount());
        return welcomePage;
    }
    
    private void Wait(){
    	//Sorry for this, but did not found anything to wait for.
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
