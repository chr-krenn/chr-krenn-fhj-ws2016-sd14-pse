package at.fhj.swd14.pse.person;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class exists just to increase code coverage
 *
 * @author Patrick Kainz
 */
public class PersonCoverageTest {

    @Test
    public void increateCoverage() {
        new Status();
        new Phonenumber();
        Person person = new Person();
        person.setId(1L);
        new Mailaddress();
        new Knowledge();
        Hobby hobby = new Hobby();
        hobby.setPerson(person);
        Assert.assertEquals(person,hobby.getPerson());
        hobby.setId(1L);
        Assert.assertEquals((Long)1L, hobby.getId());
    }

}
