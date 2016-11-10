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
	
	public AbstractPersonInformation findByValue(long personid, String value)
	{
		List<T> infos =
				entityManager.createNamedQuery(entityClass.getSimpleName()+".findByValue",this.entityClass)
				.getResultList();
		if(infos.size()==0)
			return null;
		else
			return infos.get(0);
	}

}
