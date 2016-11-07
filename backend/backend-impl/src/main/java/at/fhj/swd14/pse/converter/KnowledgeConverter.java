package at.fhj.swd14.pse.converter;

import java.util.Collection;
import java.util.LinkedList;

import at.fhj.swd14.pse.person.Knowledge;
import at.fhj.swd14.pse.person.KnowledgeDto;
import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.PersonDto;

/**
 * Converter between Knowledge and KnowledgeDto
 * @author Patrick Kainz
 *
 */
public class KnowledgeConverter {
	
	private KnowledgeConverter() {

    }
	

    public static KnowledgeDto convert(Knowledge knowledge, PersonDto personDto) {
        if (knowledge == null) {
            return null;
        }
        KnowledgeDto dto = new KnowledgeDto(knowledge.getId());
        dto.setValue(knowledge.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    public static Knowledge convert(KnowledgeDto knowledge, Person person) {
        if (knowledge == null) {
            return null;
        }
        Knowledge val =  new Knowledge(knowledge.getId());
        val.setValue(knowledge.getValue());
        val.setPerson(person);
        return val;
    }

    public static Collection<KnowledgeDto> convertToDtoList(Collection<Knowledge> knowledges, PersonDto person) {
        if (knowledges == null) {
            return null;
        }
        LinkedList<KnowledgeDto> newKnowledges = new LinkedList<KnowledgeDto>();
        for(Knowledge val : knowledges)
        	newKnowledges.add(convert(val,person));
        return newKnowledges;
    }

    public static Collection<Knowledge> convertToDoList(Collection<KnowledgeDto> knowledges, Person person) {
        if (knowledges == null) {
            return null;
        }
        LinkedList<Knowledge> newKnowledges = new LinkedList<Knowledge>();
        for(KnowledgeDto dto : knowledges)
        	newKnowledges.add(convert(dto,person));
        return newKnowledges;
    }

}
