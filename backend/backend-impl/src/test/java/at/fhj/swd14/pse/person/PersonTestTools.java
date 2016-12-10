package at.fhj.swd14.pse.person;

import java.util.LinkedList;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.user.User;

public class PersonTestTools {
	
	public static Person getDummyPerson()
	{
        return createUser(1L, "test@test.de");
	}
	
	public static Person getAnotherDummyPerson()
	{
        return createUser(2L, "test2@test.de");
	}
	
	private static Person createUser(long id, String mail) {
		User myuser = new User(id);
		myuser.setMail(mail);
        myuser.setPassword("testpassword");
        
        Department department = new Department(1L);
        department.setName("testdepartment");
        
        Person myperson = new Person(1L,myuser);
        myperson.setAdditionalMails(new LinkedList<Mailaddress>());
        myperson.getAdditionalMails().add(new Mailaddress("test2@test.de"));
        myperson.setAddress("testaddress");
        myperson.setDepartment(department);
        myperson.setFirstname("firstname");
        myperson.setHobbies(new LinkedList<Hobby>());
        myperson.getHobbies().add(new Hobby("testhobby"));
        myperson.setImageUrl("http://testimg.org");
        myperson.setKnowledges(new LinkedList<Knowledge>());
        myperson.getKnowledges().add(new Knowledge("testknowledge"));
        myperson.setLastname("lastname");
        myperson.setNumbers(new LinkedList<Phonenumber>());
        myperson.getNumbers().add(new Phonenumber("0664664664"));
        myperson.setPlace("testplace");
        myperson.setStatus(new Status("online"));
        return myperson;
	}
}
