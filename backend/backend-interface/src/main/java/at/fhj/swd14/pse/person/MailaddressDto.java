package at.fhj.swd14.pse.person;

/**
 * Dto for Mailaddress
 * @author Patrick Kainz
 *
 */
public class MailaddressDto extends AbstractPersonInformationDto {

	private static final long serialVersionUID = 1L;

	public MailaddressDto() {
		//don't do anything, its just here so that you can instantiate without params
	}

	public MailaddressDto(Long id) {
		super(id);
	}

	public MailaddressDto(Long id, String value) {
		super(id, value);
	}
	
	

}
