package at.fhj.swd14.pse.person;

import org.junit.Assert;

import at.fhj.swd14.pse.user.UserDtoTester;

public class PersonDtoTester {

    public static void assertEquals(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
        Assert.assertEquals(expected.getLastname(), actual.getLastname());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getPlace(), actual.getPlace());
        Assert.assertEquals(expected.getImageUrl(), actual.getImageUrl());

        DepartmentDtoTester.assertEquals(expected.getDepartment(), actual.getDepartment());
        StatusDtoTester.assertEquals(expected.getStatus(), actual.getStatus());
        UserDtoTester.assertEquals(expected.getUser(), actual.getUser());

        assertEmails(expected, actual);
        assertHobbies(expected, actual);
        assertKnowledges(expected, actual);
        assertPhonenumbers(expected, actual);
    }

    public static void assertPhonenumbers(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getPhonenumbers().size(), actual.getPhonenumbers().size());
        for (int i = 0; i < expected.getPhonenumbers().size(); i++)
            AbstractPersonInformationDtoTester.assertEquals(expected.getPhonenumbers().get(i), actual.getPhonenumbers().get(i));
    }

    public static void assertKnowledges(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getKnowledges().size(), actual.getKnowledges().size());
        for (int i = 0; i < expected.getKnowledges().size(); i++)
            AbstractPersonInformationDtoTester.assertEquals(expected.getKnowledges().get(i), actual.getKnowledges().get(i));
    }

    public static void assertHobbies(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getHobbies().size(), actual.getHobbies().size());
        for (int i = 0; i < expected.getHobbies().size(); i++)
            AbstractPersonInformationDtoTester.assertEquals(expected.getHobbies().get(i), actual.getHobbies().get(i));
    }

    public static void assertEmails(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getAdditionalMails().size(), actual.getAdditionalMails().size());
        for (int i = 0; i < expected.getAdditionalMails().size(); i++)
            AbstractPersonInformationDtoTester.assertEquals(expected.getAdditionalMails().get(i), actual.getAdditionalMails().get(i));
    }

}
