package at.fhj.swd14.pse.person;


import java.util.Collection;

import javax.ejb.Remote;

import at.fhj.swd14.pse.user.UserDto;

/**
 * PersonService interface class
 * @author Patrick Kainz
 * @author Patrick Papst
 */
@Remote
public interface PersonService {
	PersonDto find(long id);
	PersonDto findByUser(UserDto user);
	Collection<PersonDto> findAllUser();
}
