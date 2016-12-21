package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Converter between Phonenumber and PhonenumberDto
 *
 * @author Patrick Kainz
 */
public final class PhonenumberConverter {

    private PhonenumberConverter() {

    }

    /**
     * Converts the given Phonenumber Object to a PhonenumberDto Object
     *
     * @param phonenumber Phonenumber Object
     * @param personDto   As a hobby can not exist without a person, the PersonDto Object to assign it to
     * @return The PhonenumberDto object
     */
    public static PhonenumberDto convert(Phonenumber phonenumber, PersonDto personDto) {
        if (phonenumber == null) {
            return null;
        }
        PhonenumberDto dto = new PhonenumberDto(phonenumber.getId());
        dto.setValue(phonenumber.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    /**
     * Converts a PhonenumberDto Object to a Phonenumber Object
     * No database operations are performed
     *
     * @param phonenumber PhonenumberDto Object to convert
     * @param person      As a Phonenumber cannot exist without a person, the Person object to assign it to
     * @return Phonenumber Object
     */
    public static Phonenumber convert(PhonenumberDto phonenumber, Person person) {
        if (phonenumber == null) {
            return null;
        }
        Phonenumber val = new Phonenumber(phonenumber.getId());
        val.setValue(phonenumber.getValue());
        val.setPerson(person);
        return val;
    }

    /**
     * Converts a collection of Phonenumber Objects of the same person to a list of PhonenumberDto Objects
     *
     * @param phonenumbers Collection of Phonenumber Objects
     * @param person       As a Phonenumber cannot exist without a person, the PersonDto object to assign it to
     * @return Collection (internal type: List) of PhonenumberDto Objects
     */
    public static Collection<PhonenumberDto> convertToDtoList(Collection<Phonenumber> phonenumbers, PersonDto person) {
        if (phonenumbers == null) {
            return null;
        }
        LinkedList<PhonenumberDto> newPhonenumbers = new LinkedList<>();
        for (Phonenumber val : phonenumbers)
            newPhonenumbers.add(convert(val, person));
        return newPhonenumbers;
    }

    /**
     * Converts a collection of PhonenumberDto Objects of the same person to a list of Phonenumber objects
     * No Database operations performed
     *
     * @param phonenumbers Collection of PhonenumberDto Objects
     * @param person       As a Phonenumber cannot exist without a person, the Person object to assign it to
     * @return Collection (internal type: List) of PhonenumberDto Objects
     */
    public static Collection<Phonenumber> convertToDoList(Collection<PhonenumberDto> phonenumbers, Person person) {
        if (phonenumbers == null) {
            return null;
        }
        LinkedList<Phonenumber> newPhonenumbers = new LinkedList<>();
        for (PhonenumberDto dto : phonenumbers)
            newPhonenumbers.add(convert(dto, person));
        return newPhonenumbers;
    }

}
