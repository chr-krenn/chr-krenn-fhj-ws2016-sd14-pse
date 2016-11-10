package at.fhj.swd14.pse.repository;

import javax.ejb.Local;
import javax.ejb.Singleton;

import at.fhj.swd14.pse.person.Knowledge;
/**
 * Repository class for knowledge entity
 * @author Patrick Kainz
 *
 */
@Local
@Singleton
public class KnowledgeRepository extends AbstractPersonInformationRepository<Knowledge> {

	public KnowledgeRepository()
	{
		super(Knowledge.class);
	}
}
