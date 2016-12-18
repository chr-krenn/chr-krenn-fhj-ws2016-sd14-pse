package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.tag.Tag;

import java.util.List;

@Local
@Singleton
public class TagRepository extends AbstractRepository<Tag>{

    public TagRepository() {
        super(Tag.class);
    }

    public Tag findByName(String tagName)
    {
        List<Tag> results =  entityManager.createNamedQuery("Tag.findByName", Tag.class)
                .setParameter("name", tagName).getResultList();
        if(results.isEmpty())
            return null;
        else
            return results.get(0);
    }
}
