package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Hobby;

/**
 * Repository class for hobby entity
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class HobbyRepository extends AbstractPersonInformationRepository<Hobby> {

	protected HobbyRepository() {
		super(Hobby.class);
	}

}
