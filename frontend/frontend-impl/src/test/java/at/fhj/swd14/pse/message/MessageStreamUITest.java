package at.fhj.swd14.pse.message;

import at.fhj.swd14.pse.pageobjects.WelcomePage;
import at.fhj.swd14.pse.uitest.BaseUITest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageStreamUITest extends BaseUITest {
    private static final String TestTitle = "TestMessageTitle";
    private static final String TestContent = "TestMessageContent";
    private static final String TestCommentContent = "TestMessageCommentContent";

    @BeforeClass
    public static void setup() {
        login();
    }

    @Test
    public void testPostMessage() {
        WelcomePage welcomePage = gotoStartPage();
        insertNewMessage(welcomePage);
    }

    @Test
    public void testLikeMessage() {
        WelcomePage welcomePage = gotoStartPage();
        insertNewMessage(welcomePage);
        welcomePage.toggleLikeFirstMessage();
        assertEquals(1, welcomePage.getFirstMessageLikeCount());
        welcomePage.toggleLikeFirstMessage();
        assertEquals(0, welcomePage.getFirstMessageLikeCount());
    }

    @Test
    public void testAddCommentToMessage() {
        WelcomePage welcomePage = gotoStartPage();
        insertNewMessage(welcomePage);
        assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        welcomePage.submitCommentToFirstMessage();
        welcomePage = gotoStartPage();
        assertEquals(1, welcomePage.getFirstMessageCommentCount());
    }

    @Test
    public void testLikeCommentOfMessage() {
        WelcomePage welcomePage = gotoStartPage();
        insertNewMessage(welcomePage);
        assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        welcomePage.submitCommentToFirstMessage();
        welcomePage = gotoStartPage();
        assertEquals(1, welcomePage.getFirstMessageCommentCount());

        assertEquals(0, welcomePage.getFirstMessageFirstCommentLikeCount());
        welcomePage.toggleLikeFirstMessageFirstComment();
        assertEquals(1, welcomePage.getFirstMessageFirstCommentLikeCount());
        welcomePage.toggleLikeFirstMessageFirstComment();
        assertEquals(0, welcomePage.getFirstMessageFirstCommentLikeCount());
    }

    @Test
    public void testAddManyCommentsAndLoadMoreToMessage() {
        WelcomePage welcomePage = gotoStartPage();
        insertNewMessage(welcomePage);
        assertEquals(0, welcomePage.getFirstMessageCommentCount());
        welcomePage.addCommentToFirstMessage(TestCommentContent);
        for (int i = 0; i < 15; i++) {
            welcomePage.submitCommentToFirstMessage();
        }
        welcomePage = gotoStartPage();
        assertEquals(10, welcomePage.getFirstMessageCommentCount());
        welcomePage.firstMessageLoadMoreComments();
        assertEquals(15, welcomePage.getFirstMessageCommentCount());
    }

    private WelcomePage insertNewMessage(WelcomePage welcomePage) {
        welcomePage.insertNewMessage(TestTitle, TestContent);
        welcomePage = gotoStartPage();
        assertEquals(TestTitle, welcomePage.getFirstMessageTitle());
        assertEquals(TestContent, welcomePage.getFirstMessageContent());
        assertEquals(0, welcomePage.getFirstMessageLikeCount());
        return welcomePage;
    }
}
