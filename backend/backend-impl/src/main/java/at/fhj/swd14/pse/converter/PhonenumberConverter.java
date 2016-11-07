package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;

import at.fhj.swd14.pse.person.Phonenumber;
import at.fhj.swd14.pse.person.PhonenumberDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

/**
 * Converter between Phonenumber and PhonenumberDto
 * @author Patrick Kainz
 *
 */
public class PhonenumberConverter {
	
	private PhonenumberConverter() {

    }
	

    public static PhonenumberDto convert(Phonenumber phonenumber, PersonDto personDto) {
        if (phonenumber == null) {
            return null;
        }
        PhonenumberDto dto = new PhonenumberDto(phonenumber.getId());
        dto.setValue(phonenumber.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    public static Phonenumber convert(PhonenumberDto phonenumber, Person person) {
        if (phonenumber == null) {
            return null;
        }
        Phonenumber val =  new Phonenumber(phonenumber.getId());
        val.setValue(phonenumber.getValue());
        val.setPerson(person);
        return val;
    }

    public static Collection<PhonenumberDto> convertToDtoList(Collection<Phonenumber> phonenumbers, PersonDto person) {
        if (phonenumbers == null) {
            return null;
        }
        LinkedList<PhonenumberDto> newPhonenumbers = new LinkedList<PhonenumberDto>();
        for(Phonenumber val : phonenumbers)
        	newPhonenumbers.add(convert(val,person));
        return newPhonenumbers;
    }

    public static Collection<Phonenumber> convertToDoList(Collection<PhonenumberDto> phonenumbers, Person person) {
        if (phonenumbers == null) {
            return null;
        }
        LinkedList<Phonenumber> newPhonenumbers = new LinkedList<Phonenumber>();
        for(PhonenumberDto dto : phonenumbers)
        	newPhonenumbers.add(convert(dto,person));
        return newPhonenumbers;
    }

}
