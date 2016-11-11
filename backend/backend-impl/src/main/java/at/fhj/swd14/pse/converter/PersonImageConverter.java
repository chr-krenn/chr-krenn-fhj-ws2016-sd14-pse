package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.stream.Collectors;

import at.fhj.swd14.pse.person.PersonImage;
import at.fhj.swd14.pse.person.PersonImageDto;

public class PersonImageConverter {

	private PersonImageConverter(){};

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

    public static Collection<PersonImageDto> convertToDtoList(Collection<PersonImage> imgs) {
        if (imgs == null) {
            return null;
        }
        return imgs.stream().map(PersonImageConverter::convert).collect(Collectors.toList());
    }

    public static Collection<PersonImage> convertToList(Collection<PersonImageDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(PersonImageConverter::convert).collect(Collectors.toList());
    }
	
}
