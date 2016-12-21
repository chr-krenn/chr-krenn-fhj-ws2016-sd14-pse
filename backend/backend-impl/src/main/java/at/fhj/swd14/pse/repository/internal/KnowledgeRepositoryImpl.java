package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Knowledge;
import at.fhj.swd14.pse.person.KnowledgeRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

/**
 * Repository class for knowledge entity
 *
 * @author Patrick Kainz
 */
@Local
@Singleton
public class KnowledgeRepositoryImpl
        extends AbstractPersonInformationRepositoryImpl<Knowledge>
        implements KnowledgeRepository {

    public KnowledgeRepositoryImpl() {
        super(Knowledge.class);
    }
}
