package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;

import at.fhj.swd14.pse.person.Hobby;
import at.fhj.swd14.pse.person.HobbyDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

/**
 * Converter between Hobby and HobbyDto
 * @author Patrick Kainz
 *
 */
public class HobbyConverter {
	
	private HobbyConverter() {

    }
	

    public static HobbyDto convert(Hobby hobby, PersonDto personDto) {
        if (hobby == null) {
            return null;
        }
        HobbyDto dto = new HobbyDto(hobby.getId());
        dto.setValue(hobby.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    public static Hobby convert(HobbyDto hobby, Person person) {
        if (hobby == null) {
            return null;
        }
        Hobby val =  new Hobby(hobby.getId());
        val.setValue(hobby.getValue());
        val.setPerson(person);
        return val;
    }

    public static Collection<HobbyDto> convertToDtoList(Collection<Hobby> hobbys, PersonDto person) {
        if (hobbys == null) {
            return null;
        }
        LinkedList<HobbyDto> newHobbys = new LinkedList<HobbyDto>();
        for(Hobby val : hobbys)
        	newHobbys.add(convert(val,person));
        return newHobbys;
    }

    public static Collection<Hobby> convertToDoList(Collection<HobbyDto> hobbys, Person person) {
        if (hobbys == null) {
            return null;
        }
        LinkedList<Hobby> newHobbys = new LinkedList<Hobby>();
        for(HobbyDto dto : hobbys)
        	newHobbys.add(convert(dto,person));
        return newHobbys;
    }

}
