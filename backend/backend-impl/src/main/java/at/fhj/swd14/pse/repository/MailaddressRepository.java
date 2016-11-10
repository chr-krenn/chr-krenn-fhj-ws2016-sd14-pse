package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Mailaddress;
/**
 * Repository class for mailaddress entity
 * @author Patrick Kainz
 *
 */
public class MailaddressRepository extends AbstractPersonInformationRepository<Mailaddress> {

	public MailaddressRepository()
	{
		super(Mailaddress.class);
	}
	
}
