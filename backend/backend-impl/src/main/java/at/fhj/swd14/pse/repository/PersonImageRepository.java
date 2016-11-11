package at.fhj.swd14.pse.repository;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.PersonImage;

/**
 * Repository for person image
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class PersonImageRepository extends AbstractRepository<PersonImage> {

	protected PersonImageRepository() {
		super(PersonImage.class);
	}
	
	public PersonImage getByPersonId(Long personid)
	{
		List<PersonImage> results =  entityManager.createNamedQuery("PersonImage.findByPerson",PersonImage.class)
				.setParameter("personid", personid).getResultList();
		if(results.size()==0)
			return null;
		else
			return results.get(0);
	}

}
