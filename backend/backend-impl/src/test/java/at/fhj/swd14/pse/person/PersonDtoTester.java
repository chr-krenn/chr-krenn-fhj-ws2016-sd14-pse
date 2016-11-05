package at.fhj.swd14.pse.person;

import org.junit.Assert;

import at.fhj.swd14.pse.user.UserDtoTester;

public class PersonDtoTester {

	public static void assertEquals(PersonDto expected, PersonDto actual) {
		Assert.assertEquals(expected.getAdditionalMails().size(),actual.getAdditionalMails().size());
		for(int i = 0;i<expected.getAdditionalMails().size();i++)
			Assert.assertEquals(expected.getAdditionalMails().get(i), actual.getAdditionalMails().get(i));
		Assert.assertEquals(expected.getAddress(), actual.getAddress());
		DepartmentDtoTester.assertEquals(expected.getDepartment(),actual.getDepartment());
		Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
		Assert.assertEquals(expected.getHobbies().size(), actual.getHobbies().size());
		for(int i = 0;i<expected.getHobbies().size();i++)
			Assert.assertEquals(expected.getHobbies().get(i), actual.getHobbies().get(i));
		Assert.assertEquals(expected.getId(), actual.getId());
		Assert.assertEquals(expected.getImageUrl(), actual.getImageUrl());
		Assert.assertEquals(expected.getKnowledges().size(), actual.getKnowledges().size());
		for(int i = 0;i<expected.getKnowledges().size();i++)
			Assert.assertEquals(expected.getKnowledges().get(i), actual.getKnowledges().get(i));
		Assert.assertEquals(expected.getLastname(), actual.getLastname());
		Assert.assertEquals(expected.getPhonenumbers().size(), actual.getPhonenumbers().size());
		for(int i = 0;i<expected.getPhonenumbers().size();i++)
			Assert.assertEquals(expected.getPhonenumbers().get(i), actual.getPhonenumbers().get(i));
		Assert.assertEquals(expected.getPlace(), actual.getPlace());
		Assert.assertEquals(expected.getStatus(), actual.getStatus());
		UserDtoTester.assertEquals(expected.getUser(), actual.getUser());
	}
	
}
