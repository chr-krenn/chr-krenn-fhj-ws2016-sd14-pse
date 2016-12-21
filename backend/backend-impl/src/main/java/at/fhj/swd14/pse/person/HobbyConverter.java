package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Converter between Hobby and HobbyDto
 *
 * @author Patrick Kainz
 */
public final class HobbyConverter {

    private HobbyConverter() {

    }

    /**
     * Converts the given Hobby Object to a HobbyDto Object
     *
     * @param hobby     Hobby Object
     * @param personDto As a hobby can not exist without a person, the PersonDto Object to assign it to
     * @return The HobbyDto object
     */
    public static HobbyDto convert(Hobby hobby, PersonDto personDto) {
        if (hobby == null) {
            return null;
        }
        HobbyDto dto = new HobbyDto(hobby.getId());
        dto.setValue(hobby.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    /**
     * Converts a HobbyDto Object to a Hobby Object
     * No database operations are performed
     *
     * @param hobby  HobbyDto Object to convert
     * @param person As a Hobby cannot exist without a person, the Person object to assign it to
     * @return Hobby Object
     */
    public static Hobby convert(HobbyDto hobby, Person person) {
        if (hobby == null) {
            return null;
        }
        Hobby val = new Hobby(hobby.getId());
        val.setValue(hobby.getValue());
        val.setPerson(person);
        return val;
    }

    /**
     * Converts a collection of Hobby Objects of the same person to a list of HobbyDto Objects
     *
     * @param hobbys Collection of Hobby Objects
     * @param person As a Hobby cannot exist without a person, the PersonDto object to assign it to
     * @return Collection (internal type: List) of HobbyDto Objects
     */
    public static Collection<HobbyDto> convertToDtoList(Collection<Hobby> hobbys, PersonDto person) {
        if (hobbys == null) {
            return null;
        }
        LinkedList<HobbyDto> newHobbys = new LinkedList<>();
        for (Hobby val : hobbys)
            newHobbys.add(convert(val, person));
        return newHobbys;
    }

    /**
     * Converts a collection of HobbyDto Objects of the same person to a list of Hobby objects
     * No Database operations performed
     *
     * @param hobbys Collection of HobbyDto Objects
     * @param person As a Hobby cannot exist without a person, the Person object to assign it to
     * @return Collection (internal type: List) of HobbyDto Objects
     */
    public static Collection<Hobby> convertToDoList(Collection<HobbyDto> hobbys, Person person) {
        if (hobbys == null) {
            return null;
        }
        LinkedList<Hobby> newHobbys = new LinkedList<>();
        for (HobbyDto dto : hobbys)
            newHobbys.add(convert(dto, person));
        return newHobbys;
    }

}
