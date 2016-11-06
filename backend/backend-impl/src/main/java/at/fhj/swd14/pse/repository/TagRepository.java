package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.tag.Tag;

import javax.ejb.Local;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

@Local
@Singleton
public class TagRepository extends AbstractRepository<Tag>{

    public TagRepository() {
        super(Tag.class);
    }
}
