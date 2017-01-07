package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.news.News;
import at.fhj.swd14.pse.news.NewsAssert;
import at.fhj.swd14.pse.news.NewsConverter;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.Status;
import at.fhj.swd14.pse.user.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NewsRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<News> {

    public NewsRepositoryImplIntegrationTest() {
        super(News.class);
    }

    @Override
    protected long getDummyId(News dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<News> getRepository() {
        return new NewsRepositoryImpl();
    }

    @Override
    protected News createDummyEntity() {
        final Person author = setupAuthor();
        return buildNews(author);
    }

    private Person setupAuthor() {
        User user = new User();
        user.setMail("user@swd.com");
        user.setPassword("password");
        user.setSalt("salt");

        manager.persist(user);

        final Person author = new Person();
        author.setFirstname("first name");
        author.setLastname("last name");
        author.setStatus(new Status("online"));
        author.setUser(user);

        manager.persist(author);
        manager.flush();
        return author;
    }

    @Override
    protected void assertEquals(News expected, News actual) {
        NewsAssert.assertEquals(NewsConverter.convert(expected), NewsConverter.convert(actual));
    }

    @Override
    protected News modifyDummy(News dummy) {
        dummy.setTitle("updated title");
        dummy.setMessage("updated message");
        return dummy;
    }

    @Override
    protected void copyDummyPK(News destination, News source) {
        destination.setId(source.getId());
    }

    @Test
    public void shouldFindAllOnlineNews() {
        final Person author = setupAuthor();

        News news = buildNews(author);
        news.setTitle("expectedTitle1");
        manager.persist(news);

        news = buildNews(author);
        news.setActivation(instant(2));
        news.setTermination(instant(3));
        manager.persist(news);

        news = buildNews(author);
        news.setActivation(instant(-2));
        news.setTermination(instant(-1));
        manager.persist(news);

        news = buildNews(author);
        news.setTitle("expectedTitle2");
        news.setActivation(instant(-1));
        news.setTermination(instant(4));
        manager.persist(news);

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("onlineDate", ZonedDateTime.now().toInstant());
        final List<News> foundNews = repository.executeNamedQuery(News.FIND_ALL_ONLINE_QUERY, parameters);

        Assert.assertEquals(2, foundNews.size());
        final Iterator<News> iterator = foundNews.iterator();
        Assert.assertEquals("expectedTitle1", iterator.next().getTitle());
        Assert.assertEquals("expectedTitle2", iterator.next().getTitle());
    }

    private News buildNews(Person author) {
        final News news = new News();
        news.setTitle("news title");
        news.setMessage("news message");
        news.setActivation(instant(-1));
        news.setTermination(instant(1));
        news.setAuthor(author);
        return news;
    }

    private Instant instant(int days) {
        return ZonedDateTime.now().plusDays(days).truncatedTo(ChronoUnit.SECONDS).toInstant();
    }
}