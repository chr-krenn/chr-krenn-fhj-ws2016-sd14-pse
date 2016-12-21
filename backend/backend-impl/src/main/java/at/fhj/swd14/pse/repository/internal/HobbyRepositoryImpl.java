package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Hobby;
import at.fhj.swd14.pse.person.HobbyRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository class for hobby entity
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class HobbyRepositoryImpl
        extends AbstractPersonInformationRepositoryImpl<Hobby>
        implements HobbyRepository {

    public HobbyRepositoryImpl() {
        super(Hobby.class);
    }

}
