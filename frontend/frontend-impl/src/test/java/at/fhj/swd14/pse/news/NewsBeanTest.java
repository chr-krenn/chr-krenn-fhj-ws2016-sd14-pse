package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.person.PersonBeanTest;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Alexander on 11.11.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsBeanTest {
    @InjectMocks
    private NewsBean unitUnderTest;

    @Mock
    private NewsService newsService;

    @Mock
    private PersonService personService;

    @Mock
    private UserService userService;

    public NewsDto getDummyNews(){
        PersonBeanTest personTest = new PersonBeanTest();
        PersonDto person = personTest.getDummyPerson();

        NewsDto news = new NewsDto();
        news.setAuthor(person);
        news.setMessage("this is a testmessage");
        news.setTitle("this is a testtitle");

        return news;
    }
    @Test
    public void testEditNews()
    {
        //TODO
    }

    @Test
    public void testAddNews()
    {
        unitUnderTest.setNews(getDummyNews());
        unitUnderTest.addNews();
        Mockito.verify(newsService,Mockito.times(1)).save(Mockito.any(NewsDto.class));
    }
}
