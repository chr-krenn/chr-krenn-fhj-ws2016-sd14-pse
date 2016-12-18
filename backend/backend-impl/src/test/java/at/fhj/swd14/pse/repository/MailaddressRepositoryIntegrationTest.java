package at.fhj.swd14.pse.repository;

import at.fhj.swd14.pse.person.Mailaddress;
import at.fhj.swd14.pse.person.Person;

public class MailaddressRepositoryIntegrationTest
		extends AbstractPersonInformationRepositoryIntegrationTest<Mailaddress> {

	public MailaddressRepositoryIntegrationTest() {
		super(Mailaddress.class);
	}
	
	@Override
	protected AbstractPersonInformationRepository<Mailaddress> getInfoRepository() {
		return new MailaddressRepository();
	}

	@Override
	protected Mailaddress createDummyEntity(Person person) {
		Mailaddress address = new Mailaddress();
		address.setPerson(person);
		address.setValue("test@test.de");
		return address;
	}

}
