package at.fhj.swd14.pse.person;

import java.io.Serializable;

/**
 * Dto for knowledge
 * @author Patrick Kainz
 *
 */
public class KnowledgeDto extends AbstractPersonInformationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public KnowledgeDto() {
	}

	public KnowledgeDto(Long id) {
		super(id);
	}

}
