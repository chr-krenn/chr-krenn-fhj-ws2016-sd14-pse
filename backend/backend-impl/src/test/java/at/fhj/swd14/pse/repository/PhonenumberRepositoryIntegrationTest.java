package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.Phonenumber;

public class PhonenumberRepositoryIntegrationTest
		extends AbstractPersonInformationRepositoryIntegrationTest<Phonenumber> {

	public PhonenumberRepositoryIntegrationTest() {
		super(Phonenumber.class);
	}
	
	@Override
	protected AbstractPersonInformationRepository<Phonenumber> getInfoRepository() {
		return new PhonenumberRepository();
	}

	@Override
	protected Phonenumber createDummyEntity(Person person) {
		Phonenumber number = new Phonenumber();
		number.setPerson(person);
		number.setValue("0660660660");
		return number;
	}

}
