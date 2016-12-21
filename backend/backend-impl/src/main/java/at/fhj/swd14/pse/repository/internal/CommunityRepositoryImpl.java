package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.community.Community;
import at.fhj.swd14.pse.community.CommunityRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;

@Local
@Singleton
public class CommunityRepositoryImpl
        extends AbstractRepository<Community>
        implements CommunityRepository {

    public CommunityRepositoryImpl() {
        super(Community.class);
    }
}
