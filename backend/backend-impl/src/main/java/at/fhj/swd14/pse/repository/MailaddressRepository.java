package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Mailaddress;
/**
 * Repository class for mailaddress entity
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class MailaddressRepository extends AbstractPersonInformationRepository<Mailaddress> {

	public MailaddressRepository()
	{
		super(Mailaddress.class);
	}
	
}
