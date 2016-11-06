package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.person.*;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.repository.PersonStatusRepository;

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

    //FIXME @pkainz converter shouldn't rely on repository injection ... the checks performed with the repositories should be done in the service instead!
    // try to use #convert(PersonDto) instead
    public static Person convert(PersonDto dto, PersonStatusRepository statusRepo, PersonRepository personRepo) {
        if (dto == null) {
            return null;
        }

        Person oldPerson = personRepo.find(dto.getId());
        if (oldPerson == null)
            throw new IllegalArgumentException("Person not found " + dto.getId());

        Person person = new Person(dto.getId(), UserConverter.convert(dto.getUser()));
        person.setAddress(dto.getAddress());
        person.setDepartment(DepartmentConverter.convert(dto.getDepartment()));
        person.setFirstname(dto.getFirstname());
        person.setImageUrl(dto.getImageUrl());
        person.setLastname(dto.getLastname());
        person.setPlace(dto.getPlace());
        person.setStatus(statusRepo.findByName(dto.getStatus()));

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
        //FIXME @pkainz this would obviously lead to a status duplication
        // why does the status has to have a dedicated id column? Isn't the status' name unique enough?
        // Note: This is the version without the repository access from above method
        person.setStatus(new Status(dto.getStatus()));

        //FIXME @pkainz why converting these additional infos manually? why no separate converter?
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

    public static Collection<Person> convertToDoList(Collection<PersonDto> persons, PersonStatusRepository statusRepo, PersonRepository personRepo) {
        if (persons == null) {
            return null;
        }
        List<Person> newpersons = new LinkedList<>();

        for (PersonDto dto : persons) {
            newpersons.add(convert(dto, statusRepo, personRepo));
        }

        return newpersons;
    }

}
