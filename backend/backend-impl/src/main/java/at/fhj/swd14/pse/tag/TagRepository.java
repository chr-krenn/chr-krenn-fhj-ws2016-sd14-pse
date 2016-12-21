package at.fhj.swd14.pse.tag;

import at.fhj.swd14.pse.repository.Repository;

/**
 * @author Richard Raumberger
 */
public interface TagRepository extends Repository<Tag> {

    Tag findByName(final String tagName);
}
