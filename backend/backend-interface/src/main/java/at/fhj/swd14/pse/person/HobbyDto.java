package at.fhj.swd14.pse.person;

/**
 * Dto for hobby
 * @author Patrick Kainz
 *
 */
public class HobbyDto extends AbstractPersonInformationDto {
	
	private static final long serialVersionUID = 1L;

	public HobbyDto(Long id) {
		super(id);
	}
	
	public HobbyDto()
	{
		
	}

	public HobbyDto(Long id, String value) {
		super(id, value);
	}
	

}
