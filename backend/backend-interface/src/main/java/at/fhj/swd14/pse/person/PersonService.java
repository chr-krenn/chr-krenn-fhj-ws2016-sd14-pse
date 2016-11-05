package at.fhj.swd14.pse.person;

import at.fhj.swd14.pse.user.UserDto;

/**
 * PersonService interface class
 * @author Patrick Kainz
 *
 */
public interface PersonService {
	PersonDto find(long id);
	PersonDto findByUser(UserDto user);
}
