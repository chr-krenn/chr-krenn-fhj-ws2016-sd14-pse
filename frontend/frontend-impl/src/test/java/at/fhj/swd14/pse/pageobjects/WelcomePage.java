package at.fhj.swd14.pse.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public void insertNewMessage(String title, String content) {
        webdriver.findElement(addMessageTitle).sendKeys(title);
        webdriver.findElement(addMessageContent).sendKeys(content);
        webdriver.findElement(addMessageSubmit).click();
    }

    public String getFirstMessageTitle() {
        return webdriver.findElement(firstMessageTitle).getText();
    }

    public String getFirstMessageContent() {
        return webdriver.findElement(firstMessageContent).getText();
    }

    public int getFirstMessageLikeCount() {
        return Integer.parseInt(webdriver.findElement(firstMessageLikeCount).getText());
    }

    public void toggleLikeFirstMessage() {
        webdriver.findElement(firstMessageLikeButton).click();
    }

    public int getFirstMessageCommentCount() {
        return webdriver.findElements(By.xpath(FIRSTMESSAGECOMMENTLISTSTRING)).size();
    }

    public void addCommentToFirstMessage(String content) {
        webdriver.findElement(firstMessageAddCommentText).sendKeys(content);
    }

    public void submitCommentToFirstMessage() {
        webdriver.findElement(firstMessageAddCommentSubmit).click();
        threadWait();

    }

    public void firstMessageLoadMoreComments() {
        webdriver.findElement(firstMessageLoadMoreComments).click();
        threadWait();
    }

    public int getFirstMessageFirstCommentLikeCount() {
        return Integer.parseInt(webdriver.findElement(firstMessageFirstCommentLikeCount).getText());
    }

    public void toggleLikeFirstMessageFirstComment() {
        webdriver.findElement(firstMessageFirstCommentLikeButton).click();
        threadWait();
    }

    public void collapseCommunityDropdown() {
        webdriver.findElement(communitySelect).click();
        threadWait();
    }

    public WebElement getPrivateCommunity() {
        return webdriver.findElement(communitySelectPrivate);
    }

    public WebElement getAllCommunity() {
        return webdriver.findElement(communitySelectAll);
    }

    public void clickAllCommunity() {
        getAllCommunity().click();
        threadWait();
    }

    public void clickPrivateCommunity() {
        getPrivateCommunity().click();
        threadWait();
    }

    private void threadWait() {
        //Sorry for this, but did not found anything to wait for.
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
