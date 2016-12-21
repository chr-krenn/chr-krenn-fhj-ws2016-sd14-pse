package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Mailaddress;
import at.fhj.swd14.pse.person.MailaddressRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository class for mailaddress entity
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class MailaddressRepositoryImpl
        extends AbstractPersonInformationRepositoryImpl<Mailaddress>
        implements MailaddressRepository {

    public MailaddressRepositoryImpl() {
        super(Mailaddress.class);
    }

}
