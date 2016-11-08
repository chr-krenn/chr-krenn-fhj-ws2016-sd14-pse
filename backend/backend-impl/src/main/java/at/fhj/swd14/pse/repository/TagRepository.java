package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.tag.Tag;

@Local
@Singleton
public class TagRepository extends AbstractRepository<Tag>{

    public TagRepository() {
        super(Tag.class);
    }
}
