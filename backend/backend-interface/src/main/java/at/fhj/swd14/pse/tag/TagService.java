package at.fhj.swd14.pse.tag;

import javax.ejb.Remote;

@Remote
public interface TagService {

    /**
     * saves a tag to the database if it's not existing yet, otherwise just returns the id
     *
     * @param tag
     * @return tagId
     */
    long save(TagDto tag);

    /**
     * returns Tag with given id
     *
     * @param id
     * @return tagDto
     */
    TagDto find(long id);

    /**
     * returns Tag with given name
     *
     * @param name
     * @return tagDto
     */
    TagDto findByName(String name);

 
}
