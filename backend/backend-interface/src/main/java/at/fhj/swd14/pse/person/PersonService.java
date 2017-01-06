package at.fhj.swd14.pse.person;


import at.fhj.swd14.pse.user.UserDto;

import javax.ejb.Remote;
import java.util.Collection;

/**
 * PersonService interface class
 *
 * @author Patrick Kainz
 * @author Patrick Papst
 */
@Remote
public interface PersonService {
    /**
     * Get a person by its id
     *
     * @param id id of the person
     * @return person object or null
     */
    PersonDto find(long id);

    /**
     * Get a person by its user
     *
     * @param user UserDto object to search for
     * @return Person object or null
     */
    PersonDto findByUser(UserDto user);

    /**
     * Save the person object passed
     *
     * @param person PersonDto to save
     */
    void saveLoggedInPerson(PersonDto person);

    /**
     * Finds all contacts for a given user
     *
     * @param loggedInUserId the id of the loggedin user
     */
    Collection<PersonDto> findAllUser(long loggedInUserId);

    /**
     * Returns all possible status values
     *
     * @return List of stati
     */
    Collection<StatusDto> findAllStati();

    /**
     * Saves an image for a person
     *
     * @param person      Person to save the image for
     * @param imageData   Binary data of the image
     * @param contentType http content type of the image
     */
    void savePersonImage(PersonDto person, byte[] imageData, String contentType);

    /**
     * Returns the PersonImage of the given person
     *
     * @param personid Person id to search for
     * @return PersonImage or null
     */
    PersonImageDto getPersonImage(Long personid);

    void changeFriendState(long loggedInUserID, long otherPersonID);
}
