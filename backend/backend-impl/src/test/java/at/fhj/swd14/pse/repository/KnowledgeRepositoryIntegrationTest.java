package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Knowledge;
import at.fhj.swd14.pse.person.Person;

public class KnowledgeRepositoryIntegrationTest extends AbstractPersonInformationRepositoryIntegrationTest<Knowledge> {

	public KnowledgeRepositoryIntegrationTest() {
		super(Knowledge.class);
	}
	
	@Override
	protected AbstractPersonInformationRepository<Knowledge> getInfoRepository() {
		return new KnowledgeRepository();
	}

	@Override
	protected Knowledge createDummyEntity(Person person) {
		Knowledge knowledge = new Knowledge();
		knowledge.setPerson(person);
		knowledge.setValue("test");
		return knowledge;
	}

}
