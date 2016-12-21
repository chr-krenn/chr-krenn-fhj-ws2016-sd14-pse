package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.tag.Tag;
import at.fhj.swd14.pse.tag.TagRepository;

import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.List;

@Local
@Singleton
public class TagRepositoryImpl
        extends AbstractRepository<Tag>
        implements TagRepository {

    public TagRepositoryImpl() {
        super(Tag.class);
    }

    @Override
    public Tag findByName(final String tagName) {
        final List<Tag> results = entityManager.createNamedQuery("Tag.findByName", Tag.class)
                .setParameter("name", tagName).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
