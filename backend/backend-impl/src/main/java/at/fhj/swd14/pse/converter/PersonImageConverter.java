package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.person.PersonImage;
import at.fhj.swd14.pse.person.PersonImageDto;

public class PersonImageConverter {

	private PersonImageConverter(){};

	/**
	 * Convertes the given PersonImage object to a PersonImageDto Object
	 * @param img PersonImage object to convert
	 * @return PersonImageDto object
	 */
    public static PersonImageDto convert(PersonImage img) {
        if (img == null) {
            return null;
        }
        PersonImageDto dto = new PersonImageDto(img.getId());
        dto.setContentType(img.getContentType());
        dto.setData(img.getData());
        dto.setPerson(PersonConverter.convert(img.getPerson()));
        return dto;
    }

    /**
     * Converts the given PersonImageDto Object to a PersonImage Object
     * No Database Operations are performed
     * @param dto PersonImageDto Object
     * @return PersonImage Object
     */
    public static PersonImage convert(PersonImageDto dto) {
        if (dto == null) {
            return null;
        }
        PersonImage img = new PersonImage(dto.getId());
        img.setContentType(dto.getContentType());
        img.setData(dto.getData());
        img.setPerson(PersonConverter.convert(dto.getPerson()));
        return img;
        
    }

    /**
     * Converts the given collection of PersonImage Objects to a Collection of PersonImageDto Objects
     * @param imgs Collection of PersonImage Objects
     * @return Collection (internal type: List) of PersonImageDto Objects
     */
    public static Collection<PersonImageDto> convertToDtoList(Collection<PersonImage> imgs) {
        if (imgs == null) {
            return null;
        }
        return imgs.stream().map(PersonImageConverter::convert).collect(Collectors.toList());
    }

    /**
     * Converts the given collection of PersonImageDto objects to a Collection of PersonImage Objects
     * No Database Operations are performed
     * @param dtos Collection of PersonImageDto Objects
     * @return Collection (internal type: List) of PersonImage Objects
     */
    public static Collection<PersonImage> convertToDoList(Collection<PersonImageDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(PersonImageConverter::convert).collect(Collectors.toList());
    }
	
}
