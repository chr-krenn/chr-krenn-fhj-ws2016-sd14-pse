package at.fhj.swd14.pse.person;

import java.util.LinkedList;

import at.fhj.swd14.pse.department.Department;
import at.fhj.swd14.pse.user.User;

public class PersonTestTools {
	public static Person getDummyPerson()
	{
		User myuser = new User(1L);
        myuser.setMail("test@test.de");
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
	public static Person getDummyPerson2()
	{
		User myuser = new User(2L);
        myuser.setMail("u2@test.de");
        myuser.setPassword("testpassword");
        
        Department department = new Department(1L);
        department.setName("testdepartment");
        
        Person myperson = new Person(2L,myuser);
        myperson.setAdditionalMails(new LinkedList<Mailaddress>());
        myperson.getAdditionalMails().add(new Mailaddress("user2@test.de"));
        myperson.setAddress("testaddress2");
        myperson.setDepartment(department);
        myperson.setFirstname("firstname2");
        myperson.setHobbies(new LinkedList<Hobby>());
        myperson.getHobbies().add(new Hobby("testhobby2"));
        myperson.setImageUrl("http://testimg.org");
        myperson.setKnowledges(new LinkedList<Knowledge>());
        myperson.getKnowledges().add(new Knowledge("testknowledge2"));
        myperson.setLastname("lastname2");
        myperson.setNumbers(new LinkedList<Phonenumber>());
        myperson.getNumbers().add(new Phonenumber("0664664664"));
        myperson.setPlace("testplace2");
        myperson.setStatus(new Status(""));
        return myperson;
	}
}
