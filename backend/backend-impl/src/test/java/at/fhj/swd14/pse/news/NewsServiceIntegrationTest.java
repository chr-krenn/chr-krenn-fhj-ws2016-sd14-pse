package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.person.PersonConverter;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import static at.fhj.swd14.pse.news.NewsAssert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NewsServiceIntegrationTest extends BaseIntegrationTest {

    private NewsService newsService;

    @Before
    public void setup() throws NamingException {
        newsService = IntegrationTestUtil.getService(NewsService.class);
    }

    @Test
    public void shouldCreateFindAndUpdateNews() {
        final NewsDto expectedNews = buildNewsDto();

        long newsId = newsService.save(expectedNews);
        expectedNews.setId(newsId);
        final NewsDto actualNews = newsService.find(newsId);
        assertEquals(expectedNews, actualNews);

        expectedNews.setMessage("updatedMessage");
        expectedNews.setTitle("updatedTitle");
        expectedNews.setActivation(ZonedDateTime.now().plusDays(2).truncatedTo(ChronoUnit.SECONDS).toInstant());
        expectedNews.setTermination(ZonedDateTime.now().plusDays(3).truncatedTo(ChronoUnit.SECONDS).toInstant());

        newsId = newsService.update(expectedNews);

        final NewsDto actualUpdatedNews = newsService.find(newsId);
        assertEquals(expectedNews, actualUpdatedNews);
    }

    @Test
    public void shouldFindAllNews() {
        //Insert online news
        final NewsDto expectedOnlineNews = buildNewsDto();
        expectedOnlineNews.setId(newsService.save(expectedOnlineNews));
        //Insert offline news
        final NewsDto expectedOfflineNews = buildNewsDto();
        expectedOfflineNews.setTermination(ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).toInstant());
        expectedOfflineNews.setId(newsService.save(expectedOfflineNews));

        final Collection<NewsDto> foundNews = newsService.findAll();

        assertFalse(foundNews.isEmpty());

        assertTrue(foundNews.stream().anyMatch(newsDto -> newsDto.getId().equals(expectedOnlineNews.getId())));
        assertTrue(foundNews.stream().anyMatch(newsDto -> newsDto.getId().equals(expectedOfflineNews.getId())));
    }

    @Test
    public void shouldFindAllOnlineNews() throws InterruptedException {
        //Insert online news
        final NewsDto expectedOnlineNews = buildNewsDto();
        expectedOnlineNews.setId(newsService.save(expectedOnlineNews));
        //Insert offline news
        final NewsDto expectedOfflineNews = buildNewsDto();
        expectedOfflineNews.setTermination(ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).toInstant());
        expectedOfflineNews.setId(newsService.save(expectedOfflineNews));

        final Collection<NewsDto> foundNews = newsService.findAllOnline();

        assertFalse(foundNews.isEmpty());

        assertTrue(foundNews.stream().anyMatch(newsDto -> newsDto.getId().equals(expectedOnlineNews.getId())));
        assertFalse(foundNews.stream().anyMatch(newsDto -> newsDto.getId().equals(expectedOfflineNews.getId())));
    }

    private NewsDto buildNewsDto() {
        final NewsDto news = new NewsDto();
        news.setTitle("title");
        news.setMessage("message");
        news.setAuthor(PersonConverter.convert(getTestingPerson()));
        news.setActivation(ZonedDateTime.now().truncatedTo(ChronoUnit.SECONDS).toInstant());
        news.setTermination(ZonedDateTime.now().plusDays(2).truncatedTo(ChronoUnit.SECONDS).toInstant());
        return news;
    }

}