package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Phonenumber;

/**
 * Repository class for phonenumber entity
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class PhonenumberRepository extends AbstractPersonInformationRepository<Phonenumber> {

	public PhonenumberRepository()
	{
		super(Phonenumber.class);
	}
	
}
