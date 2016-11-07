package at.fhj.swd14.pse.person;

import java.io.Serializable;
/**
 * Dto for hobby
 * @author Patrick Kainz
 *
 */
public class HobbyDto extends AbstractPersonInformationDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public HobbyDto(Long id) {
		super(id);
	}
	
	public HobbyDto()
	{
		
	}

}
