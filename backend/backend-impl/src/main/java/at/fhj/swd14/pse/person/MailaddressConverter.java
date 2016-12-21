package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Converter between Mailaddress and MailaddressDto
 *
 * @author Patrick Kainz
 */
public final class MailaddressConverter {

    private MailaddressConverter() {

    }

    /**
     * Converts the given Mailaddress Object to a MailaddressDto Object
     *
     * @param mailaddress Mailaddress Object
     * @param personDto   As a hobby can not exist without a person, the PersonDto Object to assign it to
     * @return The MailaddressDto object
     */
    public static MailaddressDto convert(Mailaddress mailaddress, PersonDto personDto) {
        if (mailaddress == null) {
            return null;
        }
        MailaddressDto dto = new MailaddressDto(mailaddress.getId());
        dto.setValue(mailaddress.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    /**
     * Converts a MailaddressDto Object to a Mailaddress Object
     * No database operations are performed
     *
     * @param mailaddress MailaddressDto Object to convert
     * @param person      As a Mailaddress cannot exist without a person, the Person object to assign it to
     * @return Mailaddress Object
     */
    public static Mailaddress convert(MailaddressDto mailaddress, Person person) {
        if (mailaddress == null) {
            return null;
        }
        Mailaddress val = new Mailaddress(mailaddress.getId());
        val.setValue(mailaddress.getValue());
        val.setPerson(person);
        return val;
    }

    /**
     * Converts a collection of Mailaddress Objects of the same person to a list of MailaddressDto Objects
     *
     * @param mailaddresss Collection of Mailaddress Objects
     * @param person       As a Mailaddress cannot exist without a person, the PersonDto object to assign it to
     * @return Collection (internal type: List) of MailaddressDto Objects
     */
    public static Collection<MailaddressDto> convertToDtoList(Collection<Mailaddress> mailaddresss, PersonDto person) {
        if (mailaddresss == null) {
            return null;
        }
        LinkedList<MailaddressDto> newMailaddresss = new LinkedList<>();
        for (Mailaddress val : mailaddresss)
            newMailaddresss.add(convert(val, person));
        return newMailaddresss;
    }

    /**
     * Converts a collection of MailaddressDto Objects of the same person to a list of Mailaddress objects
     * No Database operations performed
     *
     * @param mailaddresss Collection of MailaddressDto Objects
     * @param person       As a Mailaddress cannot exist without a person, the Person object to assign it to
     * @return Collection (internal type: List) of MailaddressDto Objects
     */
    public static Collection<Mailaddress> convertToDoList(Collection<MailaddressDto> mailaddresss, Person person) {
        if (mailaddresss == null) {
            return null;
        }
        LinkedList<Mailaddress> newMailaddresss = new LinkedList<>();
        for (MailaddressDto dto : mailaddresss)
            newMailaddresss.add(convert(dto, person));
        return newMailaddresss;
    }

}
