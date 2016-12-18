package at.fhj.swd14.pse.repository;

import java.util.List;

import at.fhj.swd14.pse.person.AbstractPersonInformation;

/**
 * Repository class for abstractPersonInformation entitys
 * @author Patrick Kainz
 *
 * @param <T> type of the repository
 */
public abstract class AbstractPersonInformationRepository<T extends AbstractPersonInformation> extends AbstractRepository<T> {

	protected AbstractPersonInformationRepository(Class<T> entityClass) {
		super(entityClass);
	}
	
	/**
	 * Finds an AbstractPersonInformationObject by its value and personid
	 * @param personid personid to search for in the database
	 * @param value value to search for in the database
	 * @return AbstractPersonInformation Object found, or null
	 */
	public AbstractPersonInformation findByValue(long personid, String value)
	{
		//the same query exists in all AbstractPersonInformation entitys, unfortunately there is no way to do it genericly
		List<T> infos =
				entityManager.createNamedQuery(entityClass.getSimpleName()+".findByValue",this.entityClass)
				.setParameter("personid", personid)
				.setParameter("value", value)
				.getResultList();
		if(infos.isEmpty())
			return null;
		else
			return infos.get(0);
	}

}
