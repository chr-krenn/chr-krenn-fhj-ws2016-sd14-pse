package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.tag.Tag;

import javax.ejb.Stateless;

@Stateless
public class TagRepository extends AbstractRepository<Tag>{

    public TagRepository() {
        super(Tag.class);
    }
}
