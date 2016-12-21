package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Person;
import at.fhj.swd14.pse.person.Phonenumber;

public class PhonenumberRepositoryImplIntegrationTest
        extends AbstractPersonInformationRepositoryIntegrationTest<Phonenumber> {

    public PhonenumberRepositoryImplIntegrationTest() {
        super(Phonenumber.class);
    }

    @Override
    protected AbstractPersonInformationRepositoryImpl<Phonenumber> getInfoRepository() {
        return new PhonenumberRepositoryImpl();
    }

    @Override
    protected Phonenumber createDummyEntity(Person person) {
        Phonenumber number = new Phonenumber();
        number.setPerson(person);
        number.setValue("0660660660");
        return number;
    }

}
