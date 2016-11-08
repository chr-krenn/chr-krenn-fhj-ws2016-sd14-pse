package at.fhj.swd14.pse.person;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import at.fhj.swd14.pse.converter.PersonConverter;
import at.fhj.swd14.pse.repository.PersonRepository;
import at.fhj.swd14.pse.user.UserDto;

/**
 * Implementation of the service for the frontend
 * @author Patrick Kainz
 * @author Patrick Papst
 */
@Stateless
public class PersonServiceImpl implements PersonService {

	@EJB
	PersonRepository repository;
	
	@Override
	public PersonDto find(long id) {
		return PersonConverter.convert(repository.find(id));
	}

	@Override
	public PersonDto findByUser(UserDto user) {
		return PersonConverter.convert(repository.findByUserId(user.getId()));
	}

	@Override
	public Collection<PersonDto> findAllUser() {
		return PersonConverter.convertToDtoList(repository.findAll());
	}

}
