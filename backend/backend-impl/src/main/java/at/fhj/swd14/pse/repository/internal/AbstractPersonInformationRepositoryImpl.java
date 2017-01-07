package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.AbstractPersonInformation;
import at.fhj.swd14.pse.repository.AbstractPersonInformationRepository;

import java.util.List;

/**
 * Repository class for abstractPersonInformation entitys
 *
 * @param <T> type of the repository
 * @author Patrick Kainz
 */
public abstract class AbstractPersonInformationRepositoryImpl<T extends AbstractPersonInformation>
        extends AbstractRepository<T>
        implements AbstractPersonInformationRepository<T> {

    protected AbstractPersonInformationRepositoryImpl(Class<T> entityClass) {
        super(entityClass);
    }

    /**
     * Finds an AbstractPersonInformationObject by its value and personid
     *
     * @param personid personid to search for in the database
     * @param value    value to search for in the database
     * @return AbstractPersonInformation Object found, or null
     */
    @Override
    public T findByValue(long personid, String value) {
        //the same query exists in all AbstractPersonInformation entitys, unfortunately there is no way to do it genericly
        final List<T> infos =
                entityManager.createNamedQuery(entityClass.getSimpleName() + ".findByValue", this.entityClass)
                        .setParameter("personid", personid)
                        .setParameter("value", value)
                        .getResultList();
        if (infos.isEmpty()) {
            return null;
        } else {
            return infos.get(0);
        }
    }

}
