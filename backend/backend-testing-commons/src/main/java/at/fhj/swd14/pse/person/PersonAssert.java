package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.user.UserAssert;
import org.junit.Assert;

public final class PersonAssert {

    private PersonAssert() {
    }

    public static void assertEquals(PersonDto expected, PersonDto actual) {
        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstname(), actual.getFirstname());
        Assert.assertEquals(expected.getLastname(), actual.getLastname());
        Assert.assertEquals(expected.getAddress(), actual.getAddress());
        Assert.assertEquals(expected.getPlace(), actual.getPlace());
        Assert.assertEquals(expected.getImageUrl(), actual.getImageUrl());

        DepartmentAssert.assertEquals(expected.getDepartment(), actual.getDepartment());
        StatusAssert.assertEquals(expected.getStatus(), actual.getStatus());
        UserAssert.assertEquals(expected.getUser(), actual.getUser());

        assertEmails(expected, actual);
        assertHobbies(expected, actual);
        assertKnowledges(expected, actual);
        assertPhonenumbers(expected, actual);
    }

    public static void assertPhonenumbers(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getPhonenumbers().size(), actual.getPhonenumbers().size());
        for (int i = 0; i < expected.getPhonenumbers().size(); i++) {
            AbstractPersonInformationAssert.assertEquals(expected.getPhonenumbers().get(i), actual.getPhonenumbers().get(i));
        }
    }

    public static void assertKnowledges(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getKnowledges().size(), actual.getKnowledges().size());
        for (int i = 0; i < expected.getKnowledges().size(); i++) {
            AbstractPersonInformationAssert.assertEquals(expected.getKnowledges().get(i), actual.getKnowledges().get(i));
        }
    }

    public static void assertHobbies(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getHobbies().size(), actual.getHobbies().size());
        for (int i = 0; i < expected.getHobbies().size(); i++) {
            AbstractPersonInformationAssert.assertEquals(expected.getHobbies().get(i), actual.getHobbies().get(i));
        }
    }

    public static void assertEmails(PersonDto expected, PersonDto actual) {
        Assert.assertEquals(expected.getAdditionalMails().size(), actual.getAdditionalMails().size());
        for (int i = 0; i < expected.getAdditionalMails().size(); i++) {
            AbstractPersonInformationAssert.assertEquals(expected.getAdditionalMails().get(i), actual.getAdditionalMails().get(i));
        }
    }

}
