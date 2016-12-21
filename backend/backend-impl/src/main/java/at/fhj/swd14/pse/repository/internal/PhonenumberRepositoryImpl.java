package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Phonenumber;
import at.fhj.swd14.pse.person.PhonenumberRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository class for phonenumber entity
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class PhonenumberRepositoryImpl
        extends AbstractPersonInformationRepositoryImpl<Phonenumber>
        implements PhonenumberRepository {

    public PhonenumberRepositoryImpl() {
        super(Phonenumber.class);
    }

}
