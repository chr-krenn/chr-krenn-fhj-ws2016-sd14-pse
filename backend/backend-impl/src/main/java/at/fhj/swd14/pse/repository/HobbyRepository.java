package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Hobby;

/**
 * Repository class for hobby entity
 * @author Patrick Kainz
 *
 */
public class HobbyRepository extends AbstractPersonInformationRepository<Hobby> {

	protected HobbyRepository() {
		super(Hobby.class);
	}

}
