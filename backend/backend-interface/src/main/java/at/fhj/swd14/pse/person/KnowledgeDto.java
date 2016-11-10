package at.fhj.swd14.pse.person;


/**
 * Dto for knowledge
 * @author Patrick Kainz
 *
 */
public class KnowledgeDto extends AbstractPersonInformationDto {

	private static final long serialVersionUID = 1L;

	public KnowledgeDto() {
	}

	public KnowledgeDto(Long id) {
		super(id);
	}

	public KnowledgeDto(Long id, String value) {
		super(id, value);
	}

}
