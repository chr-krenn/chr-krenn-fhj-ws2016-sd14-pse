package at.fhj.swd14.pse.person;

import javax.ejb.Remote;

import at.fhj.swd14.pse.user.UserDto;

/**
 * PersonService interface class
 * @author Patrick Kainz
 *
 */
@Remote
public interface PersonService {
	PersonDto find(long id);
	PersonDto findByUser(UserDto user);
}
