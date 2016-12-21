package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.department.DepartmentConverter;
import at.fhj.swd14.pse.user.UserConverter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PersonConverterTest {

    @Test
    public void testDtoNull() {
        Person person = PersonConverter.convert((PersonDto) null);
        Assert.assertNull(person);
    }

    @Test
    public void testPersonNull() {
        PersonDto person = PersonConverter.convert((Person) null);
        Assert.assertNull(person);
    }

    private PersonDto convert(Person person) {
        PersonDto dto = new PersonDto(person.getId());
        dto.setAdditionalMails((List<MailaddressDto>) MailaddressConverter.convertToDtoList(person.getAdditionalMails(), dto));
        dto.setAddress(person.getAddress());
        dto.setDepartment(DepartmentConverter.convert(person.getDepartment()));
        dto.setFirstname(person.getFirstname());
        dto.setHobbies((List<HobbyDto>) HobbyConverter.convertToDtoList(person.getHobbies(), dto));
        dto.setImageUrl(person.getImageUrl());
        dto.setKnowledges((List<KnowledgeDto>) KnowledgeConverter.convertToDtoList(person.getKnowledges(), dto));
        dto.setLastname(person.getLastname());
        dto.setPhonenumbers((List<PhonenumberDto>) PhonenumberConverter.convertToDtoList(person.getNumbers(), dto));
        dto.setPlace(person.getPlace());
        dto.setStatus(StatusConverter.convert(person.getStatus()));
        dto.setUser(UserConverter.convert(person.getUser()));
        return dto;
    }

    private void assertEquals(Person person, PersonDto dto) {
        PersonDtoTester.assertEquals(convert(person), dto);
    }

    private void assertEquals(PersonDto dto, Person person) {
        PersonDtoTester.assertEquals(dto, convert(person));
    }

    @Test
    public void testDto() {
        PersonDto dto = convert(PersonTestTools.getDummyPerson());
        Person person = PersonConverter.convert(dto);
        assertEquals(dto, person);
    }

    @Test
    public void testPerson() {
        Person person = PersonTestTools.getDummyPerson();
        PersonDto dto = PersonConverter.convert(person);
        assertEquals(person, dto);
    }

    @Test
    public void testDtoListNull() {
        Collection<Person> values = PersonConverter.convertToDoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDoListNull() {
        Collection<PersonDto> values = PersonConverter.convertToDtoList(null);
        Assert.assertNull(values);
    }

    @Test
    public void testDtoList() {
        PersonDto dto = convert(PersonTestTools.getDummyPerson());
        List<PersonDto> list = new LinkedList<>();
        list.add(dto);
        Collection<Person> values = PersonConverter.convertToDoList(list);
        Assert.assertTrue(values instanceof List);
        List<Person> valueList = (List<Person>) values;
        Assert.assertEquals(1, valueList.size());
        assertEquals(dto, valueList.get(0));
    }

    @Test
    public void testDoList() {
        Person person = PersonTestTools.getDummyPerson();
        List<Person> list = new LinkedList<>();
        list.add(person);
        Collection<PersonDto> values = PersonConverter.convertToDtoList(list);
        Assert.assertTrue(values instanceof List);
        List<PersonDto> valueList = (List<PersonDto>) values;
        Assert.assertEquals(1, valueList.size());
        assertEquals(person, valueList.get(0));
    }


}
