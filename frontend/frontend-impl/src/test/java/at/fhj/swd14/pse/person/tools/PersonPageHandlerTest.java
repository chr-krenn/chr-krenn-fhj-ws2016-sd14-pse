package at.fhj.swd14.pse.person.tools;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.person.PersonUtil;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserService;

public class PersonPageHandlerTest {
	private PersonPageHandler handler;
	private PersonBean bean;
	private UserService userService;
	private PersonService personService;
	private FacesContext context;
    private ExternalContext extContext;
    private PersonDto person;
    
	@Before
	public void setup()
	{
		userService = Mockito.mock(UserService.class);
		personService = Mockito.mock(PersonService.class);
		bean = Mockito.mock(PersonBean.class);
		Mockito.when(bean.getUserService()).thenReturn(userService);
		Mockito.when(bean.getPersonService()).thenReturn(personService);
		handler = new PersonPageHandler(bean);
		person = PersonUtil.getDummyPerson();
    	when(userService.find(1L)).thenReturn(person.getUser());
    	when(personService.findByUser(person.getUser())).thenReturn(person);
    	context = ContextMocker.mockFacesContext();
    	extContext = mock(ExternalContext.class);
    	when(context.getExternalContext()).thenReturn(extContext);
    	DatabasePrincipal principal = Mockito.mock(DatabasePrincipal.class);
		Mockito.when(extContext.getUserPrincipal()).thenReturn(principal);
		Mockito.when(principal.getUserId()).thenReturn(person.getUser().getId());
    }

	@Test(expected=IllegalArgumentException.class)
	public void testBeanNull()
	{
		new PersonPageHandler(null);
	}
	
	@Test
	public void testUserNull()
	{
		Map<String,String> params = new HashMap<>();
		params.put("userId", person.getUser().getId().toString());
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(userService.find(person.getUser().getId())).thenReturn(null);
		handler.showPersonByUserId();
		Mockito.verify(bean,Mockito.times(1)).setPerson(null);
	}
	
	@Test
	public void testPersonNull()
	{
		Map<String,String> params = new HashMap<>();
		params.put("userId", person.getUser().getId().toString());
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		Mockito.when(personService.findByUser(person.getUser())).thenReturn(null);
		handler.showPersonByUserId();
		Mockito.verify(bean,Mockito.times(1)).setPerson(null);
	}
	
	@Test
	public void testFound()
	{
		Map<String,String> params = new HashMap<>();
		params.put("userId", person.getUser().getId().toString());
		Mockito.when(extContext.getRequestParameterMap()).thenReturn(params);
		handler.showPersonByUserId();
		Mockito.verify(bean,Mockito.times(1)).setPerson(person);
	}
	
}
