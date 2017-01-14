package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.pageobjects.WelcomePage;
import at.fhj.swd14.pse.uitest.BaseUITest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class NewsUITest extends BaseUITest {

    @BeforeClass
    public static void setup() {
        loginAdmin();
    }

    @Test
    public void shouldAddNewsAndCheckIfAddedNewsIsShown() {
        final String uuid = UUID.randomUUID().toString();
        final WelcomePage welcomePage = gotoStartPage();

        final String expectedTitle = "uitest-news-" + uuid;
        final String expectedContent = "content-" + uuid;

        welcomePage.addNews(expectedTitle, expectedContent, ZonedDateTime.now().plusDays(2).toInstant());

        assertEquals(expectedTitle, welcomePage.getFirstNewsTitle());
        assertEquals(expectedContent, welcomePage.getFirstNewsContent());
    }
}
