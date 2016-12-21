package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonImage;
import at.fhj.swd14.pse.person.PersonTestTools;
import org.junit.Assert;
import org.junit.Test;

public class PersonImageRepositoryImplIntegrationTest extends AbstractRepositoryIDIntegrationTest<PersonImage> {

    public PersonImageRepositoryImplIntegrationTest() {
        super(PersonImage.class);
    }

    @Override
    protected long getDummyId(PersonImage dummy) {
        return dummy.getId();
    }

    @Override
    protected AbstractRepository<PersonImage> getRepository() {
        return new PersonImageRepositoryImpl();
    }

    @Override
    protected PersonImage createDummyEntity() {
        PersonImage img = new PersonImage();
        Person person = PersonTestTools.getDummyPerson();
        person.setId(null);
        person.getAdditionalMails().clear();
        person.getHobbies().clear();
        person.getKnowledges().clear();
        person.getNumbers().clear();
        manager.persist(person);
        manager.flush();
        img.setPerson(person);
        img.setContentType("png");
        img.setData(new byte[1]);
        return img;
    }

    @Override
    protected void assertEquals(PersonImage expected, PersonImage actual) {
        Assert.assertEquals(expected.getPerson().getId(), actual.getPerson().getId());
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getContentType(), actual.getContentType());
        Assert.assertArrayEquals(expected.getData(), actual.getData());
    }

    @Override
    protected PersonImage modifyDummy(PersonImage dummy) {
        dummy.setContentType("jpg");
        return dummy;
    }

    @Override
    protected void copyDummyPK(PersonImage destination, PersonImage source) {
        destination.setId(source.getId());
    }

    @Test
    public void testFindByPerson() {
        PersonImage img = createDummyEntity();
        manager.persist(img);
        manager.flush();
        PersonImage otherImage = ((PersonImageRepositoryImpl) repository).getByPersonId(img.getPerson().getId());
        Assert.assertNotNull(otherImage);
        assertEquals(img, otherImage);
    }

}
