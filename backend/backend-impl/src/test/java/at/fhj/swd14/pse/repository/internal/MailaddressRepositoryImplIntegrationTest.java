package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Mailaddress;
import at.fhj.swd14.pse.person.Person;

public class MailaddressRepositoryImplIntegrationTest
        extends AbstractPersonInformationRepositoryIntegrationTest<Mailaddress> {

    public MailaddressRepositoryImplIntegrationTest() {
        super(Mailaddress.class);
    }

    @Override
    protected AbstractPersonInformationRepositoryImpl<Mailaddress> getInfoRepository() {
        return new MailaddressRepositoryImpl();
    }

    @Override
    protected Mailaddress createDummyEntity(Person person) {
        Mailaddress address = new Mailaddress();
        address.setPerson(person);
        address.setValue("test@test.de");
        return address;
    }

}
