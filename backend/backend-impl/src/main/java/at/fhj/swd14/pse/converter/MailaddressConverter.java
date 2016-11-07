package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;

import at.fhj.swd14.pse.person.Mailaddress;
import at.fhj.swd14.pse.person.MailaddressDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

/**
 * Converter between Mailaddress and MailaddressDto
 * @author Patrick Kainz
 *
 */
public class MailaddressConverter {
	
	private MailaddressConverter() {

    }
	

    public static MailaddressDto convert(Mailaddress mailaddress, PersonDto personDto) {
        if (mailaddress == null) {
            return null;
        }
        MailaddressDto dto = new MailaddressDto(mailaddress.getId());
        dto.setValue(mailaddress.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    public static Mailaddress convert(MailaddressDto mailaddress, Person person) {
        if (mailaddress == null) {
            return null;
        }
        Mailaddress val =  new Mailaddress(mailaddress.getId());
        val.setValue(mailaddress.getValue());
        val.setPerson(person);
        return val;
    }

    public static Collection<MailaddressDto> convertToDtoList(Collection<Mailaddress> mailaddresss, PersonDto person) {
        if (mailaddresss == null) {
            return null;
        }
        LinkedList<MailaddressDto> newMailaddresss = new LinkedList<MailaddressDto>();
        for(Mailaddress val : mailaddresss)
        	newMailaddresss.add(convert(val,person));
        return newMailaddresss;
    }

    public static Collection<Mailaddress> convertToDoList(Collection<MailaddressDto> mailaddresss, Person person) {
        if (mailaddresss == null) {
            return null;
        }
        LinkedList<Mailaddress> newMailaddresss = new LinkedList<Mailaddress>();
        for(MailaddressDto dto : mailaddresss)
        	newMailaddresss.add(convert(dto,person));
        return newMailaddresss;
    }

}
