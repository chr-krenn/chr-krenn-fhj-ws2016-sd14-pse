package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.BaseIntegrationTest;
import at.fhj.swd14.pse.base.IntegrationTestUtil;
import at.fhj.swd14.pse.contact.Contact;
import at.fhj.swd14.pse.user.UserConverter;
import at.fhj.swd14.pse.user.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonServiceIntegrationTest extends BaseIntegrationTest {

    private PersonService service;

    @Before
    public void setup() {
        service = IntegrationTestUtil.getService(PersonService.class);
    }

    @Test
    public void testFind() {
        Person person = getTestingPerson();

        PersonDto foundPerson = service.find(person.getId());

        validatePerson(person, foundPerson);

    }

    @Test
    public void testFindByUser() {
        Person person = getTestingPerson();

        PersonDto foundPerson = service.findByUser(UserConverter.convert(person.getUser()));

        validatePerson(person, foundPerson);
    }

    @Test
    public void testSaveLoggedInPerson() {
        EntityTransaction trans = manager.getTransaction();
        trans.begin();
        Person person = getTestingPerson();
        //just modify a field in the person
        String oldAddress = person.getAddress();
        String expectedAddress = person.getAddress() != null ? person.getAddress() + "Test" : "Test";
        person.setAddress(expectedAddress);
        service.saveLoggedInPerson(PersonConverter.convert(person));
        trans.rollback();

        //manager.refresh(person);
        person = manager.find(Person.class, person.getId());
        trans.begin();
        String actualAddress = person.getAddress();
        person.setAddress(oldAddress);
        manager.merge(person);
        manager.flush();
        trans.commit();

        Assert.assertEquals(expectedAddress, actualAddress);

    }

    @Test
    public void testFindAllUser() {
        Collection<PersonDto> persons = getAllUserContacts(getLoggedInUser());
        checkAllUserContacts(persons);
    }

    @Test
    public void testChangeFriendState() {
        UserDto userDto = getLoggedInUser();
        Collection<PersonDto> persons = getAllUserContacts(userDto);
        checkAllUserContacts(persons);
        Long userId = userDto.getId();
        Long contactId = new ArrayList<>(persons).get(0).getId();
        checkChangedContact(userId, contactId, false);
        checkChangedContact(userId, contactId, true);
    }

    @Test
    public void testFindAllStati() {
        final String[] stati = {"online", "offline", "abwesend"};

        Collection<StatusDto> svcStati = service.findAllStati();

        Assert.assertNotNull(svcStati);
        Assert.assertEquals(3, svcStati.size());

        int found = 0;

        for (StatusDto status : svcStati) {
            for (int i = 0; i < stati.length; i++) {
                if (status.getName().equals(stati[i])) {
                    found |= (int) Math.pow(2, i);
                }
            }
        }

        Assert.assertEquals((int) Math.pow(2, stati.length) - 1, found);
    }

    @Test
    public void testPersonImage() {
        EntityTransaction trans = manager.getTransaction();
        trans.begin();
        Person person = getTestingPerson();

        //save any existing images
        List<PersonImage> existingImgs = manager.createQuery("SELECT i FROM PersonImage i "
                + "											  WHERE i.person.id=:personid", PersonImage.class)
                .setParameter("personid", person.getId())
                .getResultList();

        byte[] data = {1, 2, 3, 4};

        service.savePersonImage(PersonConverter.convert(person), data, "image/jpeg");

        trans.rollback();

        List<PersonImage> newImgs = null;
        newImgs = manager.createQuery("SELECT i FROM PersonImage i "
                + "					   WHERE i.person.id=:personid", PersonImage.class)
                .setParameter("personid", person.getId())
                .getResultList();

        byte[] newData = newImgs.get(0).getData();
        String newType = newImgs.get(0).getContentType();

        trans.begin();
        //restore the state
        if (!newImgs.isEmpty())
            manager.remove(newImgs.get(0));
        if (!existingImgs.isEmpty())
            manager.persist(existingImgs.get(0));
        manager.flush();
        trans.commit();

        Assert.assertEquals(1, newImgs.size());
        Assert.assertArrayEquals(data, newData);
        Assert.assertEquals("image/jpeg", newType);
    }

    @Test
    public void testGetPersonImage() {
        Person person = getTestingPerson();

        //check if an image already exists, if not create one
        List<PersonImage> existingImgs = manager.createQuery("SELECT i FROM PersonImage i "
                + "											  WHERE i.person.id=:personid", PersonImage.class)
                .setParameter("personid", person.getId())
                .getResultList();

        EntityTransaction trans = manager.getTransaction();
        PersonImage img = null;
        if (existingImgs.isEmpty()) {
            trans.begin();
            img = new PersonImage();
            img.setId(null);
            img.setContentType("image/jpeg");
            img.setData(new byte[]{1, 2, 3, 4});
            img.setPerson(person);
            manager.persist(img);
            manager.flush();
            trans.commit();
        } else {
            img = existingImgs.get(0);
        }

        PersonImageDto dto = service.getPersonImage(person.getId());

        //restore original state
        if (existingImgs.isEmpty()) {
            trans.begin();
            manager.remove(img);
            manager.flush();
            trans.commit();
        }

        Assert.assertNotNull(dto);
        Assert.assertEquals(img.getContentType(), dto.getContentType());
        PersonAssert.assertEquals(PersonConverter.convert(person), dto.getPerson());
        Assert.assertArrayEquals(img.getData(), dto.getData());

    }

    private void validatePerson(Person expected, PersonDto actual) {
        Assert.assertNotNull(actual);
        PersonAssert.assertEquals(PersonConverter.convert(expected), actual);
    }

    private Collection<PersonDto> getAllUserContacts(UserDto userDto) {
        return service.findAllUser(userDto.getId());
    }

    private void checkAllUserContacts(Collection<PersonDto> persons) {
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }

    private UserDto getLoggedInUser() {
        Person person = getTestingPerson();
        return UserConverter.convert(person.getUser());
    }

    private void checkChangedContact(Long userId, Long contactId, boolean isEmpty) {
        service.changeFriendState(userId, contactId);
        List<Contact> contacts = manager.createQuery("SELECT c FROM Contact c WHERE c.contactPK.person1Id=:id1 AND c.contactPK.person2Id=:id2", Contact.class)
                .setParameter("id1", userId)
                .setParameter("id2", contactId)
                .getResultList();
        Assert.assertTrue(contacts.isEmpty() == isEmpty);
    }
}
