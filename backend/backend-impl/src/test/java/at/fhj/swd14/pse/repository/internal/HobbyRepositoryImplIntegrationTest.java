package at.fhj.swd14.pse.repository.internal;

import at.fhj.swd14.pse.person.Hobby;
import at.fhj.swd14.pse.person.Person;

public class HobbyRepositoryImplIntegrationTest extends AbstractPersonInformationRepositoryIntegrationTest<Hobby> {

    public HobbyRepositoryImplIntegrationTest() {
        super(Hobby.class);
    }

    @Override
    protected AbstractPersonInformationRepositoryImpl<Hobby> getInfoRepository() {
        return new HobbyRepositoryImpl();
    }

    @Override
    protected Hobby createDummyEntity(Person person) {
        Hobby hobby = new Hobby();
        hobby.setValue("test");
        hobby.setPerson(person);
        return hobby;
    }

}
