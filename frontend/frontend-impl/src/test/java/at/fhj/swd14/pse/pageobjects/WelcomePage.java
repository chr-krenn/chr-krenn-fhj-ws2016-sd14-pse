package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WelcomePage extends AbstractPage {

	private final By profileLink = By.linkText("Profile");
	private final By usersLink = By.linkText("Users");
	private final By addMessageTitle = By.id("j_addMessage:j_addMessageTitle");
	private final By addMessageContent = By.id("j_addMessage:j_addMessageContent");
	private final By addMessageSubmit = By.id("j_addMessage:j_addMessageSubmit");
	private final By firstMessageTitle = By.id("messagestream_scroller:scroller_message:0:messagetitle");
	private final By firstMessageContent = By.id("messagestream_scroller:scroller_message:0:messagecontent");
	private final By firstMessageLikeCount = By.id("messagestream_scroller:scroller_message:0:form_like_message:text_like_message");
	private final By firstMessageLikeButton = By.id("messagestream_scroller:scroller_message:0:form_like_message:btn_Like_message");
	private final By firstMessageAddCommentText = By.id("messagestream_scroller:scroller_message:0:form_newcomment:text_newcomment_content");
	private final By firstMessageAddCommentSubmit = By.id("messagestream_scroller:scroller_message:0:form_newcomment:text_newcomment_submit");
	private final By firstMessageLoadMoreComments = By.id("messagestream_scroller:scroller_message:0:scroller_comment:scroller_load_more_comments");
	private final By firstMessageFirstCommentLikeCount = By.id("messagestream_scroller:scroller_message:0:scroller_comment:0:form_like_comment:text_like_comment");
	private final By firstMessageFirstCommentLikeButton = By.id("messagestream_scroller:scroller_message:0:scroller_comment:0:form_like_comment:submit_like_comment");
	private final By communitySelect = By.id("j_selectCommunity:select_community");
	private final By communitySelectPrivate = By.id("j_selectCommunity:select_community_1");
	private final By communitySelectAll = By.id("j_selectCommunity:select_community_0");
	
	private static final String FIRSTMESSAGECOMMENTLISTSTRING = "//div[@id='messagestream_scroller:scroller_message:0:scroller_comment']/div/ul/li";

	public WelcomePage(WebDriver webdriver) {
		super(webdriver, "welcome");
	}

    public ProfilePage changeToOwnProfilePage() {
    	webdriver.findElement(profileLink).click();
    	return new ProfilePage(webdriver);
    }
    
    public UsersPage changeToUsersPage() {
    	webdriver.findElement(usersLink).click();
    	return new UsersPage(webdriver);
    }
    
    public void InsertNewMessage(String title, String content){
    	webdriver.findElement(addMessageTitle).sendKeys(title);
    	webdriver.findElement(addMessageContent).sendKeys(content);
    	webdriver.findElement(addMessageSubmit).click();
    }
    
    public String GetFirstMessageTitle(){
    	return webdriver.findElement(firstMessageTitle).getText();
    }
    
    public String GetFirstMessageContent(){
    	return webdriver.findElement(firstMessageContent).getText();
    }
    
    public int GetFirstMessageLikeCount(){
    	return Integer.parseInt(webdriver.findElement(firstMessageLikeCount).getText());
    }
    
    public void ToggleLikeFirstMessage(){
    	webdriver.findElement(firstMessageLikeButton).click();
    }
    
    public int GetFirstMessageCommentCount(){
    	return webdriver.findElements(By.xpath(FIRSTMESSAGECOMMENTLISTSTRING)).size();
    }
    
    public void AddCommentToFirstMessage(String content){
    	webdriver.findElement(firstMessageAddCommentText).sendKeys(content);
    }
    
    public void SubmitCommentToFirstMessage(){
    	webdriver.findElement(firstMessageAddCommentSubmit).click();
    }
    
    public void FirstMessageLoadMoreComments(){
    	webdriver.findElement(firstMessageLoadMoreComments).click();
    	Wait();
    }
    
    public int GetFirstMessageFirstCommentLikeCount(){
    	return Integer.parseInt(webdriver.findElement(firstMessageFirstCommentLikeCount).getText());
    }
    
    public void ToggleLikeFirstMessageFirstComment(){
    	webdriver.findElement(firstMessageFirstCommentLikeButton).click();
    	Wait();
    }
    
    public void CollapseCommunityDropdown(){
    	webdriver.findElement(communitySelect).click();
    }
    
    public WebElement GetPrivateCommunity(){
    	return webdriver.findElement(communitySelectPrivate);
    }
    
    public WebElement GetAllCommunity(){
    	return webdriver.findElement(communitySelectAll);
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
