package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.general.RequestContextMocker;
import at.fhj.swd14.pse.person.CommonPersonBeanTest;
import at.fhj.swd14.pse.person.PersonBeanTest;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.primefaces.context.ApplicationContext;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import org.primefaces.util.AjaxRequestBuilder;
import org.primefaces.util.CSVBuilder;
import org.primefaces.util.StringEncrypter;
import org.primefaces.util.WidgetBuilder;

import javax.faces.application.FacesMessage;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;

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



    @Before
    public void setUp(){
        RequestContext.setCurrentInstance(new RequestContextMocker(), ContextMocker.mockFacesContext());
    }

    public NewsDto getDummyNews(){
        PersonDto person = CommonPersonBeanTest.getDummyPerson();

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

    //@Ignore("stubbing of RequestContext not done yet")
    @Test
    public void testAddNews()
    {
        unitUnderTest.setNews(getDummyNews());
        UserDto user = unitUnderTest.getNews().getAuthor().getUser();
        unitUnderTest.setCurrentUser(user);
        unitUnderTest.addNews();
        Mockito.verify(newsService,Mockito.times(1)).save(any(NewsDto.class));
    }
}
