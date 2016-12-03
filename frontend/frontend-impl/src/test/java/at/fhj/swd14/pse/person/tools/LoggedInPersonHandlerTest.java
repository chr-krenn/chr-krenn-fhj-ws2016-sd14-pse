package at.fhj.swd14.pse.person.tools;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.person.DepartmentDtoTester;
import at.fhj.swd14.pse.person.PersonBean;
import at.fhj.swd14.pse.person.PersonBeanTest;
import at.fhj.swd14.pse.person.PersonDto;
import at.fhj.swd14.pse.person.PersonDtoTester;
import at.fhj.swd14.pse.person.PersonService;
import at.fhj.swd14.pse.person.StatusDto;
import at.fhj.swd14.pse.person.StatusDtoTester;
import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserService;

public class LoggedInPersonHandlerTest {
	
	private LoggedInPersonPageHandler handler;
	private PersonBean bean;
	private UserService userService;
	private PersonService personService;
	private DepartmentService departmentService;
	private PersonVerifier verifier;
	private FacesContext context;
    private ExternalContext extContext;
    private PersonDto person;
    private List<StatusDto> stati;
    private List<DepartmentDto> deps;
	
	@Before
	public void setup()
	{
		userService = Mockito.mock(UserService.class);
		personService = Mockito.mock(PersonService.class);
		departmentService = Mockito.mock(DepartmentService.class);
		verifier = Mockito.mock(PersonVerifier.class);
		bean = Mockito.mock(PersonBean.class);
		Mockito.when(bean.getUserService()).thenReturn(userService);
		Mockito.when(bean.getPersonService()).thenReturn(personService);
		Mockito.when(bean.getDepartmentService()).thenReturn(departmentService);
		Mockito.when(bean.getVerifier()).thenReturn(verifier);
		handler = new LoggedInPersonPageHandler(bean);
		person = PersonBeanTest.getDummyPerson();
    	when(userService.find(1L)).thenReturn(person.getUser());
    	when(personService.findByUser(person.getUser())).thenReturn(person);
    	context = ContextMocker.mockFacesContext();
    	extContext = mock(ExternalContext.class);
    	when(context.getExternalContext()).thenReturn(extContext);
    	DatabasePrincipal principal = Mockito.mock(DatabasePrincipal.class);
		Mockito.when(extContext.getUserPrincipal()).thenReturn(principal);
		Mockito.when(principal.getUserId()).thenReturn(person.getUser().getId());
    	stati = new LinkedList<StatusDto>();
    	stati.add(new StatusDto("online"));
    	deps = new LinkedList<DepartmentDto>();
    	DepartmentDto dep = new DepartmentDto(1L);
    	dep.setName("test");
    	deps.add(dep);
    	when(personService.findAllStati()).thenReturn(stati);
    	when(departmentService.findAll()).thenReturn(deps);
    	when(bean.getDepartments()).thenReturn(deps);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBeanNull()
	{
		new LoggedInPersonPageHandler(null);
	}
	
	@Test
    public void testLoggedInPerson()
    {    	
    	String path = handler.showLoggedInPerson();
    	Assert.assertEquals("/user", path);
    	Mockito.verify(bean,Mockito.times(1)).setPerson(person);
    	Mockito.verify(bean,Mockito.times(1)).setStati(stati);
    	Mockito.verify(bean,Mockito.times(1)). setDepartments(deps);
    }
	
	@Test
	public void testPersonNotFound()
	{
		when(userService.find(1L)).thenReturn(null);
		handler.showLoggedInPerson();
    	Mockito.verify(bean,Mockito.times(1)).setPerson((PersonDto)Mockito.notNull());
	}
}
