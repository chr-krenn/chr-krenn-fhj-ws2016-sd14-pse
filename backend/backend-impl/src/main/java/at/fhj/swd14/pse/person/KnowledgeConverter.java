package at.fhj.swd14.pse.person;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Converter between Knowledge and KnowledgeDto
 *
 * @author Patrick Kainz
 */
public final class KnowledgeConverter {

    private KnowledgeConverter() {

    }

    /**
     * Converts the given Knowledge Object to a KnowledgeDto Object
     *
     * @param knowledge Knowledge Object
     * @param personDto As a hobby can not exist without a person, the PersonDto Object to assign it to
     * @return The KnowledgeDto object
     */
    public static KnowledgeDto convert(Knowledge knowledge, PersonDto personDto) {
        if (knowledge == null) {
            return null;
        }
        KnowledgeDto dto = new KnowledgeDto(knowledge.getId());
        dto.setValue(knowledge.getValue());
        dto.setPerson(personDto);
        return dto;
    }

    /**
     * Converts a KnowledgeDto Object to a Knowledge Object
     * No database operations are performed
     *
     * @param knowledge KnowledgeDto Object to convert
     * @param person    As a Knowledge cannot exist without a person, the Person object to assign it to
     * @return Knowledge Object
     */
    public static Knowledge convert(KnowledgeDto knowledge, Person person) {
        if (knowledge == null) {
            return null;
        }
        Knowledge val = new Knowledge(knowledge.getId());
        val.setValue(knowledge.getValue());
        val.setPerson(person);
        return val;
    }

    /**
     * Converts a collection of Knowledge Objects of the same person to a list of KnowledgeDto Objects
     *
     * @param knowledges Collection of Knowledge Objects
     * @param person     As a Knowledge cannot exist without a person, the PersonDto object to assign it to
     * @return Collection (internal type: List) of KnowledgeDto Objects
     */
    public static List<KnowledgeDto> convertToDtoList(Collection<Knowledge> knowledges, PersonDto person) {
        if (knowledges == null) {
            return null;
        }
        LinkedList<KnowledgeDto> newKnowledges = new LinkedList<>();
        for (Knowledge val : knowledges)
            newKnowledges.add(convert(val, person));
        return newKnowledges;
    }

    /**
     * Converts a collection of KnowledgeDto Objects of the same person to a list of Knowledge objects
     * No Database operations performed
     *
     * @param knowledges Collection of KnowledgeDto Objects
     * @param person     As a Knowledge cannot exist without a person, the Person object to assign it to
     * @return Collection (internal type: List) of KnowledgeDto Objects
     */
    public static List<Knowledge> convertToDoList(Collection<KnowledgeDto> knowledges, Person person) {
        if (knowledges == null) {
            return null;
        }
        LinkedList<Knowledge> newKnowledges = new LinkedList<>();
        for (KnowledgeDto dto : knowledges)
            newKnowledges.add(convert(dto, person));
        return newKnowledges;
    }

}
