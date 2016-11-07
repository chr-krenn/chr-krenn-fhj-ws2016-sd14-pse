package at.fhj.swd14.pse.person;

import java.io.Serializable;

/**
 * Dto for phone number
 * @author Patrick Kainz
 *
 */
public class PhonenumberDto extends AbstractPersonInformationDto implements Serializable{

	private static final long serialVersionUID = 1L;

	public PhonenumberDto() {
	}

	public PhonenumberDto(Long id) {
		super(id);
	}
	
	

}
