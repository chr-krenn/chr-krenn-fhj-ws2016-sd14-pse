package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.community.Community;

@Local
@Singleton
public class CommunityRepository extends AbstractRepository<Community>{

    public CommunityRepository() {
        super(Community.class);
    }
}
