package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Hobby;
import at.fhj.swd14.pse.person.Person;

public class HobbyRepositoryIntegrationTest extends AbstractPersonInformationRepositoryIntegrationTest<Hobby> {

	public HobbyRepositoryIntegrationTest() {
		super(Hobby.class);
	}
	
	@Override
	protected AbstractPersonInformationRepository<Hobby> getInfoRepository() {
		return new HobbyRepository();
	}

	@Override
	protected Hobby createDummyEntity(Person person) {
		Hobby hobby = new Hobby();
		hobby.setValue("test");
		hobby.setPerson(person);
		return hobby;
	}

}
