package at.fhj.swd14.pse.person;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import javax.faces.context.ExternalContext;

import org.mockito.Mockito;

import at.fhj.swd14.pse.department.DepartmentDto;
import at.fhj.swd14.pse.department.DepartmentService;
import at.fhj.swd14.pse.general.ContextMocker;
import at.fhj.swd14.pse.security.DatabasePrincipal;
import at.fhj.swd14.pse.user.UserDto;
import at.fhj.swd14.pse.user.UserService;

public class PersonUtil {

    public static PersonDto getDummyPerson() {
        UserDto myuser = new UserDto(1L);
        myuser.setMail("test@test.de");
        myuser.setPassword("testpassword");

        DepartmentDto department = new DepartmentDto(1L);
        department.setName("testdepartment");

        PersonDto myperson = new PersonDto(1L);
        myperson.setUser(myuser);
        myperson.setAdditionalMails(new LinkedList<>());
        myperson.getAdditionalMails().add(new MailaddressDto(1L, "test2@test.de"));
        myperson.setAddress("testaddress");
        myperson.setDepartment(department);
        myperson.setFirstname("firstname");
        myperson.setHobbies(new LinkedList<>());
        myperson.getHobbies().add(new HobbyDto(1L, "testhobby"));
        myperson.setImageUrl("http://testimg.org");
        myperson.setKnowledges(new LinkedList<>());
        myperson.getKnowledges().add(new KnowledgeDto(1L, "testknowledge"));
        myperson.setLastname("lastname");
        myperson.setPhonenumbers(new LinkedList<>());
        myperson.getPhonenumbers().add(new PhonenumberDto(1L, "0664664664"));
        myperson.setPlace("testplace");
        myperson.setStatus(new StatusDto("Online"));
        return myperson;
    }

    public static CommonPersonBeanTestData setupServices(UserService userService, PersonService personService, DepartmentService departmentService) {
        CommonPersonBeanTestData data = new CommonPersonBeanTestData();
        data.setPerson(getDummyPerson());
        when(userService.find(1L)).thenReturn(data.getPerson().getUser());
        when(personService.findByUser(data.getPerson().getUser())).thenReturn(data.getPerson());
        data.setContext(ContextMocker.mockFacesContext());
        data.setExtContext(mock(ExternalContext.class));
        when(data.getContext().getExternalContext()).thenReturn(data.getExtContext());
        DatabasePrincipal principal = Mockito.mock(DatabasePrincipal.class);
        Mockito.when(data.getExtContext().getUserPrincipal()).thenReturn(principal);
        Mockito.when(principal.getUserId()).thenReturn(data.getPerson().getUser().getId());
        data.setStati(new LinkedList<>());
        data.getStati().add(new StatusDto("online"));
        data.setDeps(new LinkedList<>());
        DepartmentDto dep = new DepartmentDto(1L);
        dep.setName("test");
        data.getDeps().add(dep);
        when(personService.findAllStati()).thenReturn(data.getStati());
        when(departmentService.findAll()).thenReturn(data.getDeps());
        return data;
    }

}
