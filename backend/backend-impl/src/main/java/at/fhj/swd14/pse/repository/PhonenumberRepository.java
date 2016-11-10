package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Phonenumber;

/**
 * Repository class for phonenumber entity
 * @author Patrick Kainz
 *
 */
public class PhonenumberRepository extends AbstractPersonInformationRepository<Phonenumber> {

	public PhonenumberRepository()
	{
		super(Phonenumber.class);
	}
	
}
