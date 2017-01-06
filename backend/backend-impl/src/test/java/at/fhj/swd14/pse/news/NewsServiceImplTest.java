package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.user.User;
import at.fhj.swd14.pse.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static at.fhj.swd14.pse.news.NewsAssert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

    private static final long ID = 1337L;
    private static final Instant TEST_INSTANT = Instant.now();

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsRepository newsRepository;

    @Test
    public void shouldSaveNews() {
        final NewsDto news = buildNewsDto();
        assertEquals(ID, newsService.save(news));

        final ArgumentCaptor<News> newsCaptor = ArgumentCaptor.forClass(News.class);
        verify(newsRepository, times(1)).save(newsCaptor.capture());

        assertEquals(NewsConverter.convert(news), newsCaptor.getValue());
    }

    @Test(expected = NewsServiceException.class)
    public void shouldThrowExceptionIfNewsCannotBeSaved() {
        newsService.save(null);
    }

    @Test
    public void shouldUpdateNews() {
        final NewsDto news = buildNewsDto();
        assertEquals(ID, newsService.update(news));

        final ArgumentCaptor<News> newsCaptor = ArgumentCaptor.forClass(News.class);
        verify(newsRepository, times(1)).update(newsCaptor.capture());

        assertEquals(NewsConverter.convert(news), newsCaptor.getValue());
    }

    @Test(expected = NewsServiceException.class)
    public void shouldThrowExceptionIfNewsCannotBeUpdated() {
        newsService.update(null);
    }

    @Test
    public void shouldFindSingleNews() {
        when(newsRepository.find(ID)).thenReturn(buildNews());
        final NewsDto news = newsService.find(ID);

        assertEquals(buildNewsDto(), news);
    }

    @Test(expected = NewsServiceException.class)
    public void shouldConvertExceptionsWhenFindingASingleNews() {
        doThrow(IllegalArgumentException.class).when(newsRepository).find(ID);
        newsService.find(ID);
    }

    @Test
    public void shouldFindAll() {
        when(newsRepository.findAll()).thenReturn(Arrays.asList(buildNews(), buildNews(), buildNews()));
        final Collection<NewsDto> news = newsService.findAll();

        assertEquals(3, news.size());
        news.forEach(newsDto -> assertEquals(buildNewsDto(), newsDto));
    }

    @Test(expected = NewsServiceException.class)
    public void shouldConvertExceptionsWhenFindingAllNews() {
        doThrow(IllegalArgumentException.class).when(newsRepository).findAll();
        newsService.findAll();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFindAllOnline() {
        final ArgumentCaptor<Map> parameterCaptor = ArgumentCaptor.forClass(Map.class);

        when(newsRepository.executeNamedQuery(eq(News.FIND_ALL_ONLINE_QUERY), parameterCaptor.capture()))
                .thenReturn(Arrays.asList(buildNews(), buildNews()));
        final Collection<NewsDto> news = newsService.findAllOnline();

        assertEquals(2, news.size());

        final Map<String, Object> parameters = parameterCaptor.getValue();
        assertEquals(1, parameters.size());
        assertEquals("onlineDate", parameters.keySet().iterator().next());
        assertTrue((((Instant) parameters.values().iterator().next()).toEpochMilli() - System.currentTimeMillis()) < 100);

        final Iterator<NewsDto> iterator = news.iterator();
        assertEquals(buildNewsDto(), iterator.next());
        assertEquals(buildNewsDto(), iterator.next());
    }

    @Test(expected = NewsServiceException.class)
    @SuppressWarnings("unchecked")
    public void shouldConvertExceptionsWhenFindingAllOnlineNews() {
        doThrow(IllegalArgumentException.class).when(newsRepository).executeNamedQuery(any(String.class), any(Map.class));
        newsService.findAllOnline();
    }

    private NewsDto buildNewsDto() {
        final NewsDto news = new NewsDto();
        news.setActivation(TEST_INSTANT);
        final PersonDto author = new PersonDto();
        author.setId(ID);
        author.setUser(new UserDto(ID));
        author.setFirstname("Derp");
        news.setAuthor(author);
        news.setMessage("Message");
        news.setTermination(TEST_INSTANT);
        news.setTitle("title");
        news.setId(ID);
        return news;
    }

    private News buildNews() {
        final News news = new News();
        news.setActivation(TEST_INSTANT);
        final Person author = new Person();
        author.setId(ID);
        author.setUser(new User(ID));
        author.setFirstname("Derp");
        news.setAuthor(author);
        news.setMessage("Message");
        news.setTermination(TEST_INSTANT);
        news.setTitle("title");
        news.setId(ID);
        return news;
    }

}