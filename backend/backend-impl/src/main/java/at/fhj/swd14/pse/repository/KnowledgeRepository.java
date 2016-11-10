package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Knowledge;
/**
 * Repository class for knowledge entity
 * @author Patrick Kainz
 *
 */
public class KnowledgeRepository extends AbstractPersonInformationRepository<Knowledge> {

	public KnowledgeRepository()
	{
		super(Knowledge.class);
	}
}
