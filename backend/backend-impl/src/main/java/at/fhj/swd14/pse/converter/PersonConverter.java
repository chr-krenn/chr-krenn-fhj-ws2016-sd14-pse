package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.person.*;

public class PersonConverter {

    private PersonConverter() {
    }

    public static PersonDto convert(Person person) {
        if (person == null) {
            return null;
        }
        PersonDto dto = new PersonDto(person.getId());
        dto.setAddress(person.getAddress());
        dto.setFirstname(person.getFirstname());
        dto.setLastname(person.getLastname());
        dto.setImageUrl(person.getImageUrl());
        dto.setPlace(person.getPlace());
        dto.setStatus(person.getStatus().getName());
        dto.setDepartment(DepartmentConverter.convert(person.getDepartment()));
        dto.setUser(UserConverter.convert(person.getUser()));

        if (person.getAdditionalMails() != null) {
            for (Mailaddress address : person.getAdditionalMails()) {
                dto.getAdditionalMails().add(address.getValue());
            }
        }

        if (person.getHobbies() != null) {
            for (Hobby hobby : person.getHobbies()) {
                dto.getHobbies().add(hobby.getValue());
            }
        }

        if (person.getKnowledges() != null) {
            for (Knowledge knowledge : person.getKnowledges()) {
                dto.getKnowledges().add(knowledge.getValue());
            }
        }

        if (person.getNumbers() != null) {
            for (Phonenumber number : person.getNumbers()) {
                dto.getPhonenumbers().add(number.getValue());
            }
        }

        return dto;
    }

    public static Person convert(PersonDto dto) {
        if (dto == null) {
            return null;
        }

        Person person = new Person(dto.getId(), UserConverter.convert(dto.getUser()));
        person.setAddress(dto.getAddress());
        person.setDepartment(DepartmentConverter.convert(dto.getDepartment()));
        person.setFirstname(dto.getFirstname());
        person.setImageUrl(dto.getImageUrl());
        person.setLastname(dto.getLastname());
        person.setPlace(dto.getPlace());
        person.setStatus(new Status(dto.getStatus()));

        //@pkainz why converting these additional infos manually? why no separate converter?
        //Because they are only used in the context of persons, so why create another class for them
        //if noone except this class is going to use it.
        person.setAdditionalMails(new LinkedList<>());
        for (String mail : dto.getAdditionalMails()) {
            Mailaddress address = new Mailaddress();
            address.setValue(mail);
            person.getAdditionalMails().add(address);
        }

        person.setHobbies(new LinkedList<>());
        for (String hobby : dto.getHobbies()) {
            Hobby newhobby = new Hobby();
            newhobby.setValue(hobby);
            person.getHobbies().add(newhobby);
        }

        person.setKnowledges(new LinkedList<>());
        for (String knowledge : dto.getKnowledges()) {
            Knowledge newknowledge = new Knowledge();
            newknowledge.setValue(knowledge);
            person.getKnowledges().add(newknowledge);
        }

        person.setNumbers(new LinkedList<>());
        for (String number : dto.getPhonenumbers()) {
            Phonenumber newnumber = new Phonenumber();
            newnumber.setValue(number);
            person.getNumbers().add(newnumber);
        }

        return person;
    }

    public static Collection<PersonDto> convertToDtoList(Collection<Person> persons) {
        if (persons == null) {
            return null;
        }
        return persons.stream().map(PersonConverter::convert).collect(Collectors.toList());
    }

    public static Collection<Person> convertToDoList(Collection<PersonDto> persons) {
        if (persons == null) {
            return null;
        }
        List<Person> newpersons = new LinkedList<>();

        for (PersonDto dto : persons) {
            newpersons.add(convert(dto));
        }

        return newpersons;
    }

}
